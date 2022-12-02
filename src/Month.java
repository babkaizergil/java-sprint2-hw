public class Month {
    public String item;
    public boolean isExpense;
    public int quantity;
    public int price;
    public int year;
    public int month;

    public Month(String item, boolean isExpense, int quantity, int price, int year, int month) {
        this.item = item;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.price = price;
        this.year = year;
        this.month = month;
    }
}
