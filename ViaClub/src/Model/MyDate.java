package Model;
import java.util.GregorianCalendar;

public class MyDate {
    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public MyDate() {
        MyDate date = today();
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
    }

    public static MyDate today() {
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentDay = currentDate.get(GregorianCalendar.DATE);
        int currentMonth = currentDate.get(GregorianCalendar.MONTH) + 1;
        int currentYear = currentDate.get(GregorianCalendar.YEAR);

        return new MyDate(currentDay, currentMonth, currentYear);
    }

    public void setDay(int day) {
        if (day == 0) {
            throw new IllegalArgumentException();
        } else
            this.day = day;
    }

    public void setMonth(int month) {
        if (day == 0) {
            throw new IllegalArgumentException();
        } else
            this.month = month;
    }

    public void setYear(int year) {
        if (day == 0) {
            throw new IllegalArgumentException();
        } else
            this.year = year;
    }

    public String toString() {
        return this.day + "/" + this.month + "/" + this.year;
    }

    public MyDate copy() {
        return new MyDate(this.day, this.month, this.year);
    }
}

