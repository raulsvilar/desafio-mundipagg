package raulsvilar.desafiomundipagg.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.data.models.Merchant;
import raulsvilar.desafiomundipagg.data.responses.MerchantsResponse;
import raulsvilar.desafiomundipagg.service.MerchantService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MerchantViewModel extends BaseObservable implements Callback<MerchantsResponse>{
    private MerchantService merchantService;
    @Inject Retrofit retrofit;
    private boolean loadingService;
    private MerchantsResponse merchantsResponse;
    private OnMerchantsListener mCallback;

    private final String TAG = getClass().getSimpleName();

    @Bindable
    public boolean isLoadingService() {
        return loadingService;
    }

    public void setLoadingService(boolean loading) {
        this.loadingService = loading;
        notifyPropertyChanged(BR.loadingService);
    }

    public interface OnMerchantsListener{
        void onGetMerchantsSuccess(List<Merchant> merchants);
        void onGetMerchantsFailed(int code);
    }

    public MerchantViewModel() {
        App.getComponent().inject(this);
        merchantService = retrofit.create(MerchantService.class);
    }



    @Override
    public void onResponse(Call<MerchantsResponse> call, Response<MerchantsResponse> response) {
        setLoadingService(false);
        if (response.code() <= 204) {
            merchantsResponse = response.body();
            mCallback.onGetMerchantsSuccess(merchantsResponse.getMerchants());
        } else mCallback.onGetMerchantsFailed(response.code());
    }

    @Override
    public void onFailure(Call<MerchantsResponse> call, Throwable t) {
        Log.e(TAG, "Falhou", t);
        setLoadingService(false);
        mCallback.onGetMerchantsFailed(503);
    }

    public void searchMerchants(@NonNull String customerKey, @NonNull String accessToken,
                                @NonNull String term) {

        Map<String, String> map = new HashMap<>();
        map.put("pageNumber", "1");
        map.put("merchant", term);
        setLoadingService(true);
        merchantService.searchMerchant(customerKey,
                String.format("%s %s","Bearer",accessToken), map).enqueue(this);
    }

    public void setOnMerchantsListener(OnMerchantsListener listener) {
        mCallback = listener;
    }
}
