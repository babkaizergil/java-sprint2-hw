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
        return topItem;
    }
    //Самый прибыльный товар, то есть товар для которого is_expense == false,
    // а произведение количества (quantity) на сумму (sum_of_one) максимально.
    // Вывести название товара и сумму;

    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}
