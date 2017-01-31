package raulsvilar.desafiomundipagg.service;

import android.support.annotation.NonNull;

import java.util.Map;

import raulsvilar.desafiomundipagg.data.responses.MerchantsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MerchantService {
    @GET("{customerKey}/merchants")
    @Headers("IsSandboxEnabled: true")
    Call<MerchantsResponse> searchMerchant(
            @Path("customerKey") @NonNull String customerKey,
            @Header("Authorization") String token,
            @QueryMap Map<String, String> options);
}
