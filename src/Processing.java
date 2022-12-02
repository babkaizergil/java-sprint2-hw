public class Processing {
    public YearReader yearReader;
    public MonthReader monthReader;

    public Processing(YearReader yearReader, MonthReader monthReader) {
        this.yearReader = yearReader;
        this.monthReader = monthReader;
    }
    public boolean check() {
        return false;
    }
}
