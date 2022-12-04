import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthReader {
    public ArrayList<Month> allMonths = new ArrayList<>();
    public void loadFile (int year, int month, String path){
        String content = readFileContents(path);
        String[] monthContent = content.split("\r?\n");
        for(int i = 1; i < monthContent.length; i++) {
            String line = monthContent[i];
            String[] parts = line.split(",");
            String item = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int price = Integer.parseInt(parts[3]);
            Month mont = new Month(item, isExpense, quantity, price, year, month);
            allMonths.add(mont);
        }
    }
    public String getTopItem(int month){
        HashMap<String, Integer> items = new HashMap<>();
        for (Month all : allMonths) {
            if(all.month == month){
                if(!all.isExpense){
                    items.put(all.item, (all.price * all.quantity));
               }
           }
        }
        String topItem = null;
        for (String item : items.keySet()) {
            if(topItem == null) {
                topItem = item;
                continue;
            }
            if (items.get(topItem) < items.get(item)) {
                topItem = item;
            }
        }
        topItem = topItem + " : " + (items.get(topItem));
        return topItem;
    }
    public String getMaxExpense(int month){
        HashMap<String, Integer> expenses = new HashMap<>();
        for (Month all : allMonths) {
            if(all.month == month) {
                if(all.isExpense) {
                    expenses.put(all.item, (all.price * all.quantity));
                }
            }
        }
        String maxExpense = null;
        for(String item : expenses.keySet()){
            if(maxExpense == null) {
                maxExpense = item;
                continue;
            }
            if (expenses.get(maxExpense) < expenses.get(item)) {
                maxExpense = item;
            }
        }
        maxExpense = maxExpense + " : " + expenses.get(maxExpense);
        return maxExpense;
    }

    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public String getMonthName(int month) {
        String monthName;
        switch (month) {
            case 1:
                monthName = "Январь";
                break;
            case 2:
                monthName = "Февраль";
                break;
            case 3:
                monthName = "Март";
                break;
            default:
                monthName = "Неопознанный месяц";
        }
        return monthName;
    }
}