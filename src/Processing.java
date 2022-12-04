import java.util.HashMap;

public class Processing {
    public YearReader yearReader;
    public MonthReader monthReader;

    public Processing(YearReader yearReader, MonthReader monthReader) {
        this.yearReader = yearReader;
        this.monthReader = monthReader;
    }

    public boolean check() {
        boolean check = true;
        HashMap<Integer, Integer> expenseInMonth = new HashMap<>();
        HashMap<Integer, Integer> incomeInMonth = new HashMap<>();
        for (Month month : monthReader.allMonths) {

            int money = month.quantity * month.price;
            if (month.isExpense) {
                Integer monthExpense = expenseInMonth.get(month.month);
                if (monthExpense != null) {
                    expenseInMonth.put(month.month, monthExpense + money);
                } else {
                    expenseInMonth.put(month.month, money);
                }
            } else {
                Integer monthIncome = incomeInMonth.get(month.month);
                if (monthIncome != null) {
                    incomeInMonth.put(month.month, monthIncome + money);
                } else {
                    incomeInMonth.put(month.month, money);
                }
            }
        }
        HashMap<Integer, Integer> expenseInYear = new HashMap<>();
        HashMap<Integer, Integer> incomeInYear = new HashMap<>();
        for (Year year : yearReader.allYear) {
            if (year.isExpense) {
                expenseInYear.put(year.month, year.amount);
            } else {
                incomeInYear.put(year.month, year.amount);
            }
        }
        for (Integer monthNumber : expenseInMonth.keySet()) {
            int expenseByMonth = expenseInMonth.get(monthNumber);
            Integer expenseByYear = expenseInYear.get(monthNumber);
            if (expenseByYear == null) {
                System.out.println("Расход есть в месячном отчете " + ", но отсутствует в годовом отчете ");
                check = false;
            } else if (expenseByMonth != expenseByYear) {
                System.out.println("В " + monthNumber + " месяце расход составил " + expenseByMonth +
                        " по месячному отчету, по годовому: " + expenseByYear);
                check = false;
            }
        }
        for (Integer monthNumber : incomeInMonth.keySet()) {
            int incomeByMonth = incomeInMonth.get(monthNumber);
            Integer incomeByYear = incomeInYear.getOrDefault(monthNumber, 0);
            if (incomeByYear == null) {
                System.out.println("Доход есть в месячном отчете " + ", но отсутствует в годовом отчете ");
                check = false;
            } else if (incomeByMonth != incomeByYear) {
                System.out.println("В " + monthNumber + " месяце доход составил " + incomeByMonth +
                        " по месячному отчету, по годовому: " + incomeByYear);
                check = false;
            }
        }
        return check;
    }
}