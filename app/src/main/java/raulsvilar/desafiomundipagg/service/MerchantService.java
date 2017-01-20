package raulsvilar.desafiomundipagg.service;

import android.support.annotation.NonNull;

import java.util.Map;

import raulsvilar.desafiomundipagg.model.MerchantsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MerchantService {
    @GET("{customerKey}/merchants")
    Call<MerchantsResponse> searchMerchant(
            @Path("customerKey") @NonNull String customerKey,
            @QueryMap Map<String, String> options);
}
