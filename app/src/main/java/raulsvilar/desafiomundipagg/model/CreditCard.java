package raulsvilar.desafiomundipagg.model;

/**
 * Created by raulsvilar on 19/01/17.
 */

public class CreditCard {

    private String number;
    private String printedName;
    private int expMonth;
    private int expYear;
    private String brand;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrinted_name() {
        return printedName;
    }

    public void setPrinted_name(String printed_name) {
        this.printedName = printed_name;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
