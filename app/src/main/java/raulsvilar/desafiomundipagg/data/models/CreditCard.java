package raulsvilar.desafiomundipagg.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by raulsvilar on 19/01/17.
 */

public class CreditCard {

    @SerializedName("CreditCardNumber")
    private String number;
    @SerializedName("HolderName")
    private String holderName;
    @SerializedName("ExpMonth")
    private int expMonth;
    @SerializedName("ExpYear")
    private int expYear;
    @SerializedName("CreditCardBrand")
    private String brand;
    @SerializedName("SecurityCode")
    private int cvv;

    public CreditCard() {

    }

    public CreditCard(String number, String holderName, int expMonth, int expYear, String brand, int cvv) {
        this.number = number;
        this.holderName = holderName;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.brand = brand;
        this.cvv = cvv;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrinted_name() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
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
