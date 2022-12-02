import java.util.Scanner;
public class Main {

    public static void main(String[] args) {


        //MonthReader monthReader = new MonthReader();

       // Processing processing = new Processing(yearReader, monthReader);

        System.out.println("Введите команду:");
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        YearReader yearReader = null;
        MonthReader monthReader = null;
        while (input != 007) {
            if (input == 1) {
                monthReader = new MonthReader();
                for(int i = 1; i < 13; i++) {
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
                //todo
            }
            else if (input == 4) {
                if(monthReader != null) {
                    System.out.println("ИНФОРМАЦИЯ О МЕСЯЧНОМ ОТЧЕТЕ");
                    System.out.println();
                    System.out.println("Самый прибыльный товар:" + monthReader.getTopItem(1));
                }
                //todo
            }
            else if (input == 5) {
                if(yearReader != null) {
                    System.out.println("ИНФОРМАЦИЯ О ГОДОВОМ ОТЧЕТЕ");
                    System.out.println();
                    yearReader.getProfit();
                    System.out.println("Средний расход в году: " + yearReader.getAvgExpense());
                    System.out.println("Средний доход в году: " + yearReader.getAvgIncome());
                }
            }
            else {
                System.out.println("Команда не известна");
            }
            printMenu();                                // печатаем меню ещё раз
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

