package raulsvilar.desafiomundipagg.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import raulsvilar.desafiomundipagg.App;

public class Transaction {

    @SerializedName("CreditCardTransactionCollection")
    private List<Item> creditCardTransactionCollection;
    @Inject
    @SerializedName("Buyer")
    Buyer buyer;

    public Transaction() {
        App.getComponent().inject(this);
        creditCardTransactionCollection = new ArrayList<>();
    }

    public void setSale(int valueInCents, int installmentCount, List<CreditCard> cards,
                        String userName, String userEmail) {
        for (CreditCard card: cards) {
            creditCardTransactionCollection.add(new Item(valueInCents, card, installmentCount));
        }
        buyer.setName(userName);
        buyer.setEmail(userEmail);
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
