package raulsvilar.desafiomundipagg.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import raulsvilar.desafiomundipagg.model.User;

public class UserViewModel extends BaseObservable {

    private User mUser;

    @Bindable
    public String getUserName() {
        return mUser.getName();
    }



}
