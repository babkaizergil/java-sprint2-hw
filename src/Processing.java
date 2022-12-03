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
            if(month.isExpense) {
                expenseInMonth.put(month.month, (month.quantity * month.price));
            } else {
                incomeInMonth.put(month.month, (month.quantity * month.price));
            }
        }
        int expense = 0;
        for (Integer exp : expenseInMonth.keySet()) {
            expense += exp;
        }
        int income = 0;
        for (Integer inc : incomeInMonth.keySet()) {
            income += inc;
        }
        HashMap<Integer, Integer> expenseInYear = new HashMap<>();
        HashMap<Integer, Integer> incomeInYear = new HashMap<>();
        for (Year year : yearReader.allYear) {
            if(year.isExpense) {
                expenseInYear.put(year.month, year.amount);
            } else {
                incomeInYear.put(year.month, year.amount);
            }
        }
        if(expenseInYear == null) {
            System.out.println("Расход есть в месячном отчете " + ", но отсутствует в годовом отчете ");
            check = false;
        }
        for (Integer monthNumber : expenseInMonth.keySet()) {
            int expenseByMonth = expenseInMonth.get(monthNumber);
            int expenseByYear = expenseInYear.getOrDefault(monthNumber, 0);
            if(expenseByMonth != expenseByYear) {
                System.out.println("В " + monthNumber + " месяце расход составил " + expenseByMonth +
                        " по месячному отчету, по годовому: " + expenseByYear);
            }
            check = false;
        }
        if(incomeInYear == null) {
            System.out.println("Доход есть в месячном отчете " + ", но отсутствует в годовом отчете ");
            check = false;
        }
        for (Integer monthNumber : incomeInMonth.keySet()) {
            int incomeByMonth = incomeInMonth.get(monthNumber);
            int incomeByYear = incomeInYear.getOrDefault(monthNumber,0);
            if(incomeByMonth != incomeByYear) {
                System.out.println("В " + monthNumber + " месяце доход составил " + incomeByMonth +
                        " по месячному отчету, по годовому: " + incomeByYear);
            }
            check = false;
            }
        return check;
    }
}

//Подсчитывать две суммы: общие доходы и общие расходы по каждому из месяцев.
//Сверять полученные суммы с суммой доходов и расходов в отчёте по году.