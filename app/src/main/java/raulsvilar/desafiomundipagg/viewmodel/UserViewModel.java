package raulsvilar.desafiomundipagg.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.model.User;
import raulsvilar.desafiomundipagg.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserViewModel extends BaseObservable {
    private String TAG = getClass().getSimpleName();
    @Inject Retrofit retrofit;
    private UserService userService;
    @Inject User mUser;

    public UserViewModel() {
        App.getComponent().inject(this);
        userService = retrofit.create(UserService.class);
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
    public String getUserCompany() {
        return mUser.getCompany();
    }

    public void setUserCompany(String company) {
        mUser.setCompany(company);
        notifyPropertyChanged(BR.userCompany);
    }

    public void registerUser() {
        Log.d(TAG, mUser.toString());
        userService.createUser(mUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, new Gson().toJson(mUser));
                if (response.code() >= 400) {
                    try {
                        Log.e(TAG, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else Log.d(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Falhou", t);
            }
        });
    }

}
