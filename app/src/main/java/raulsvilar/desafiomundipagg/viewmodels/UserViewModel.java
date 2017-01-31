package raulsvilar.desafiomundipagg.viewmodels;

import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.data.models.User;
import raulsvilar.desafiomundipagg.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserViewModel extends BaseObservable implements Callback<User>{

    public final static String USER_REFRESH_TOKEN = "refresh_token";
    public final static String USER_NAME = "user_name";
    public final static String USER_EMAIL = "user_email";


    private String TAG = getClass().getSimpleName();
    @Inject Retrofit retrofit;
    @Inject SharedPreferences sharedPreferences;
    private UserService userService;
    @Inject User mUser;
    private boolean loading;
    private boolean withRefresh;
    private OnUserListener mCallback;

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        setLoading(false);
        if (response.code() <= 204) {
            mUser = response.body();
            sharedPreferences.edit().putString(USER_REFRESH_TOKEN, mUser.getRefreshToken()).apply();
            sharedPreferences.edit().putString(USER_EMAIL, mUser.getEmail()).apply();
            sharedPreferences.edit().putString(USER_NAME, mUser.getName()).apply();
            Log.d(TAG, response.body().toString());
            mCallback.onSuccess(mUser.getCustomerKey(), mUser.getAccessToken());
        } else if (!withRefresh) {
            mCallback.onFailed(response.code());
        } else withRefresh = false;
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        setLoading(false);
        mCallback.onFailed(503);
    }

    public interface OnUserListener {
        void onFailed(int code);
        void onSuccess(String customerKey, String accessToken);
    }

    public UserViewModel() {
        App.getComponent().inject(this);
        userService = retrofit.create(UserService.class);
        restoreUser();
    }

    public void setOnUserListener(@NonNull OnUserListener listener) {
        mCallback = listener;
    }

    @Bindable
    public String getUserName() {
        return mUser.getName();
    }

    public void setUserName(String name) {
        mUser.setName(name);
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserPassword() {
        return mUser.getPassword();
    }

    public void setUserPassword(String password) {
        mUser.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    @Bindable
    public String getUserEmail() {
        return mUser.getEmail();
    }

    public void setUserEmail(String email) {
        mUser.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public String getUserCompany() {
        return mUser.getCompany();
    }

    public void setUserCompany(String company) {
        mUser.setCompany(company);
        notifyPropertyChanged(BR.userCompany);
    }

    public void registerUser() {
        Log.d(TAG, mUser.toString());
        setLoading(true);
        userService.createUser(mUser).enqueue(this);
    }

    public void restoreUser() {
        String refresh = sharedPreferences.getString(USER_REFRESH_TOKEN, null);
        if (refresh != null) {
            setLoading(true);
            withRefresh = true;
            Map<String, String> map = new HashMap<>();
            map.put("refreshToken", refresh);
            userService.loginWithRefreshToken(map).enqueue(this);
        }
    }

    public void authenticateUser() {
        setLoading(true);
        Log.d(TAG, mUser.toString());
        userService.loginWithCredentials(mUser).enqueue(this);
    }

}
