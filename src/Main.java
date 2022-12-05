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
            switch (input) {
                case 1:
                    monthReader = new MonthReader();
                    for (int i = 1; i < 4; i++) {
                        String monthNumber = (i < 10) ? "0" + i : String.valueOf(i);
                        monthReader.loadFile(2021, i, "resources/m.2021" + monthNumber + ".csv");
                    }
                    System.out.println("Месячные отчеты успешно загружены");
                    break;
                case 2:
                    yearReader = new YearReader("resources/y.2021.csv");
                    System.out.println("Годовой отчет успешно загружен");
                    break;
                case 3:
                    if (monthReader != null && yearReader != null) {
                        Processing processing = new Processing(yearReader, monthReader);
                        if (processing.check()) {
                            System.out.println("Операция успешно завершена.");
                        }
                    } else {
                        System.out.println("Отчеты не загружены. Необходимо считать отчеты");
                    }
                    break;
                case 4:
                    if (monthReader != null) {
                        for (int i = 1; i < 4; i++) {
                            System.out.println("ИНФОРМАЦИЯ О МЕСЯЧНОМ ОТЧЕТЕ ЗА " + monthReader.getMonthName(i) + " месяц");
                            System.out.println("Самый прибыльный товар: " + monthReader.getTopItem(i));
                            System.out.println("Самая большая трата: " + monthReader.getMaxExpense(i));
                            System.out.println();
                        }
                    }
                    break;
                case 5:
                    if (yearReader != null) {
                        System.out.println("ИНФОРМАЦИЯ О ГОДОВОМ ОТЧЕТЕ"); // как вывести номер года из имени файла
                        System.out.println();
                        System.out.println(yearReader.allYear.get(0).yearNumber + " ГОД");
                        yearReader.getProfit();
                        System.out.println("Средний расход в году: " + yearReader.getAvgExpense());
                        System.out.println("Средний доход в году: " + yearReader.getAvgIncome());
                    }
                    break;
                default:
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