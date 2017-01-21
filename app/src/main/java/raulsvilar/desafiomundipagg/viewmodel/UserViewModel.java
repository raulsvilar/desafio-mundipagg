package raulsvilar.desafiomundipagg.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.tool.expr.SymbolExpr;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

import raulsvilar.desafiomundipagg.model.User;

public class UserViewModel extends BaseObservable {
    private String TAG = getClass().getSimpleName();
    private User mUser;

    public UserViewModel() {
        mUser = new User();
    }

    @Bindable
    public String getUserName() {
        return mUser.getName();
    }

    public void setmUserName(String name) {
        mUser.setUsername(name);
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

    public void registerUser() {
        Log.d(TAG, mUser.getName()+" "+mUser.getPassword());
    }

}
