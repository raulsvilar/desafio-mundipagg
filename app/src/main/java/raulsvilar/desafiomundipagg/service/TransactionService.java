package raulsvilar.desafiomundipagg.service;


import okhttp3.ResponseBody;
import raulsvilar.desafiomundipagg.data.models.Transaction;
import raulsvilar.desafiomundipagg.data.responses.TransactionResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface TransactionService {
    @POST("Sale/")
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
            "IsSandboxEnabled: true"
            })
    Call<ResponseBody> sendTransaction(@Header("MerchantKey") String merchantKey,
                                       @Body Transaction transaction);
}
