package raulsvilar.desafiomundipagg.viewmodels;

import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.ResponseBody;
import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.data.models.CreditCard;
import raulsvilar.desafiomundipagg.data.models.Transaction;
import raulsvilar.desafiomundipagg.data.models.User;
import raulsvilar.desafiomundipagg.service.TransactionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static raulsvilar.desafiomundipagg.R.string.email;

public class TransactionViewModel extends BaseObservable implements Callback<ResponseBody> {

    private List<CreditCard> cardList;
    @Inject Transaction transaction;
    @Inject SharedPreferences sharedPreferences;
    private TransactionService service;
    @Inject @Named("transactionRetrofit")
    Retrofit retrofit;
    private String merchantKey;
    private String value;
    private String installments;
    private boolean loadingTransaction;
    private OnTransactionListener mCallback;

    public interface OnTransactionListener{
        void onSuccess();
        void onFailed(int code);
    }

    public TransactionViewModel(String merchantKey) {
        App.getComponent().inject(this);
        this.merchantKey = merchantKey;
        cardList = new ArrayList<>();
        service = retrofit.create(TransactionService.class);
    }

    public void setOnTransactionListener(OnTransactionListener listener) {
        mCallback = listener;
    }

    @Bindable
    public boolean isLoadingTransaction() {
        return loadingTransaction;
    }

    public void setLoadingTransaction(boolean loadingTransaction) {
        this.loadingTransaction = loadingTransaction;
        notifyPropertyChanged(BR.loadingTransaction);
    }

    @Bindable
    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public String getInstallments() {
        return installments;
    }

    public void setInstallments(String installments) {
        this.installments = installments;
        notifyPropertyChanged(BR.installments);
    }

    public void buy() {
        if (getValue() != null && getInstallments() != null) {
            BigDecimal value = new BigDecimal(getValue());
            value = value.multiply(BigDecimal.valueOf(100));
            String email = sharedPreferences.getString(UserViewModel.USER_NAME, null);
            String name = sharedPreferences.getString(UserViewModel.USER_EMAIL, null);
            transaction.setSale(value.toBigInteger().intValue(),
                    Integer.parseInt(getInstallments()), cardList, name, email);
            sendTransaction();
        } else mCallback.onFailed(0);
    }

    private void sendTransaction() {
        setLoadingTransaction(true);
        Log.d("TransactionViewModel", new Gson().toJson(transaction));
        service.sendTransaction(merchantKey, transaction).enqueue(this);
    }

    public void addCard(CreditCard card) {
        cardList.add(card);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        setLoadingTransaction(false);
        if(response.code() <= 204) {
            mCallback.onSuccess();
        } else mCallback.onFailed(response.code());
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        setLoadingTransaction(false);
        mCallback.onFailed(503);
    }
}
