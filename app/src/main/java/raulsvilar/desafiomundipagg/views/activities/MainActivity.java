package raulsvilar.desafiomundipagg.views.activities;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import raulsvilar.desafiomundipagg.R;
import raulsvilar.desafiomundipagg.Utils;
import raulsvilar.desafiomundipagg.views.fragments.SignInFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Utils.changeFragment(getSupportFragmentManager(), R.id.container,
                    SignInFragment.newInstance(), false, null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
