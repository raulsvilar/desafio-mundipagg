package raulsvilar.desafiomundipagg.views.activities;

import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import raulsvilar.desafiomundipagg.App;
import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.viewmodels.UserViewModel;
import raulsvilar.desafiomundipagg.views.fragments.SignInFragment;

public class MainActivity extends AppCompatActivity implements UserViewModel.OnUserLogout {

    @Inject
    UserViewModel userViewModel;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);
        userViewModel.setOnUserLogoutListener(this);
        if (savedInstanceState == null) {
            Utils.changeFragment(getSupportFragmentManager(), R.id.container,
                    SignInFragment.newInstance(), false, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void logout() {
        String access = String.format("%s %s","Bearer",
                sharedPreferences.getString(UserViewModel.USER_ACCESS_TOKEN, null));
        if(access != null)
            userViewModel.logout(access);
    }

    @Override
    public void onLogout() {
        Utils.changeFragment(getSupportFragmentManager(), R.id.container,
                SignInFragment.newInstance(), false, null);
    }

    @Override
    public void onLogoutFail() {
        Utils.createAlert(this, getString(R.string.logout), getString(R.string.fail_logout))
                .setPositiveButton(R.string.ok, null).show();
    }
}
