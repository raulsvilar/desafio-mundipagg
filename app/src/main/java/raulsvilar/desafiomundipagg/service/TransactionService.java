package raulsvilar.desafiomundipagg.service;


//https://sandbox.mundipaggone.com/Sale

import raulsvilar.desafiomundipagg.data.models.Transaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
    {
    "CreditCardTransactionCollection": [
        {
            "AmountInCents": 10000,
            "CreditCard": {
                "CreditCardBrand": "Visa",
                "CreditCardNumber": "4111111111111111",
                "ExpMonth": 10,
                "ExpYear": 22,
                "HolderName": "LUKE SKYWALKER",
                "SecurityCode": "123"
            },
            "InstallmentCount": 1
        }
    ],
    "Order": {
        "OrderReference": "NumeroDoPedido"
    }
}

*/
public interface TransactionService {
    @POST
    Call<Transaction> sendTransaction(@Body Transaction transaction);
}
