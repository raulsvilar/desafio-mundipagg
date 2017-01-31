package raulsvilar.desafiomundipagg.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

    @SerializedName("CreditCardTransactionCollection")
    private List<Item> creditCardTransactionCollection;

    public Transaction() {
        creditCardTransactionCollection = new ArrayList<>();
    }

    public void setSale(int valueInCents, int installmentCount, List<CreditCard> cards) {
        for (CreditCard card: cards) {
            creditCardTransactionCollection.add(new Item(valueInCents, card, installmentCount));
        }
    }
    private class Item {
        @SerializedName("AmountInCents")
        int amountInCents;
        @SerializedName("CreditCard")
        CreditCard creditCard;
        @SerializedName("InstallmentCount")
        int installmentCount;

        Item(int amountInCents, CreditCard creditCard, int installmentCount) {
            this.amountInCents = amountInCents;
            this.creditCard = creditCard;
            this.installmentCount = installmentCount;
        }
    }
}
