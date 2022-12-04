import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        System.out.println("Введите команду:");
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        YearReader yearReader = null;
        MonthReader monthReader = null;
        while (input != 007) {
            if (input == 1) {
                monthReader = new MonthReader();
                for(int i = 1; i < 4; i++) {
                    String monthNumber = (i<10)? "0"+ i: String.valueOf(i);
                    monthReader.loadFile(2021, i,"resources/m.2021" + monthNumber + ".csv");
                }
                System.out.println("Месячные отчеты успешно загружены");
            }
            else if (input == 2) {
                yearReader = new YearReader("resources/y.2021.csv");
                System.out.println("Годовой отчет успешно загружен");
            }
            else if (input == 3) {
                if(monthReader != null && yearReader != null) {
                    Processing processing = new Processing(yearReader, monthReader);
                    if (processing.check()) {
                        System.out.println("Операция успешно завершена.");
                    }
                }
            }
            else if (input == 4) {
                if(monthReader != null) {
                    for(int i = 1; i < 4; i++) {
                        System.out.println("ИНФОРМАЦИЯ О МЕСЯЧНОМ ОТЧЕТЕ ЗА " + monthReader.getMonthName(i) + " месяц"); //todo свитч для месяцев названий
                        System.out.println("Самый прибыльный товар: " + monthReader.getTopItem(i));
                        System.out.println("Самая большая трата: " + monthReader.getMaxExpense(i));
                        System.out.println();
                    }
                }
            }
            else if (input == 5) {
                if(yearReader != null) {
                    System.out.println("ИНФОРМАЦИЯ О ГОДОВОМ ОТЧЕТЕ"); // как вывести номер года из имени файла
                    System.out.println();
                    yearReader.getProfit();
                    System.out.println("Средний расход в году: " + yearReader.getAvgExpense());
                    System.out.println("Средний доход в году: " + yearReader.getAvgIncome());
                }
            }
            else {
                System.out.println("Команда не известна");
            }
            printMenu();
            input = scanner.nextInt();
        }
    }
    static void printMenu(){
        System.out.println("1 - Считать все месячные отчёты;\n" +
                "2 - Считать годовой отчёт;\n" +
                "3 - Сверить отчёты;\n" +
                "4 - Вывести информацию о всех месячных отчётах;\n" +
                "5 - Вывести информацию о годовом отчёте");
    }
}

