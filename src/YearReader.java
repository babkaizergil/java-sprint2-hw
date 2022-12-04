import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearReader {
    public ArrayList<Year> allYear = new ArrayList<>();             //список куда сложу объекты класса Year

    public YearReader(String path) {
        String content = readFileContents(path);                    //считываю из файла по пути в строку
        if(content != null){
        String[] yearContent = content.split("\r?\n");              // строку разбиваю на массив строк
        for (int i = 1; i < yearContent.length; i++) {
            String line = yearContent[i];                           // раскладываю новые строки по массиву "01,1593150,false"
            String[] parts = line.split(",");                   //строку разбиваю на кусочки информации "01","1593150","false"
            int month = Integer.parseInt(parts[0]);                                    //месяц
            int amount = Integer.parseInt(parts[1]);                  //сумма
            boolean isExpense = Boolean.parseBoolean(parts[2]);         //расходли ?

            Year year = new Year(month, amount, isExpense);         //создаю объект year, передаю параметры
            allYear.add(year);                                      //складываю объект в список
            }
        } else {
            System.out.println("ПОТРАЧЕНО");
        }
    }

    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

     public void getProfit() {
        for(int i = 1; i < (allYear.size() / 2) + 1; i++){
            int income = 0;
            int expense = 0;
            for (Year year : allYear) {
                if(year.month != i) {
                    continue;
                }
                if(year.isExpense) {
                    expense += year.amount;
                } else {
                    income += year.amount;
                }
            }
            int profit = income - expense;
            System.out.println("Прибыль составила " + profit + " в " + i + " месяце");
        }
    }
    public double getAvgExpense() {
        int expense = 0;
        float count = 0;
        for (Year year : allYear) {
            if(year.isExpense){
                expense += year.amount;
                count++;
            }
        }
        return expense / count;
    }

    public double getAvgIncome() {
        int income = 0;
        float count = 0;
        for (Year year : allYear) {
            if(!year.isExpense) {
                income += year.amount;
                count++;
            }
        }
        return income / count;
    }

}