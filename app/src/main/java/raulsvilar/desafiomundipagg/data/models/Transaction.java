package raulsvilar.desafiomundipagg.data.models;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
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
        int AmountInCents;
        CreditCard creditCard;
        int InstallmentCount;

        Item(int amountInCents, CreditCard creditCard, int installmentCount) {
            AmountInCents = amountInCents;
            this.creditCard = creditCard;
            InstallmentCount = installmentCount;
        }
    }
}
