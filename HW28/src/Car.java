public class Car {
    private String mark;
    private int year;
    private double price;

    public Car(String mark, int year, double price) {
        this.mark = mark;
        this.year = year;
        this.price = price;
    }

    public String getMark() {
        return mark;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
