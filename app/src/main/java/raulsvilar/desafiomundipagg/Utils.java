package raulsvilar.desafiomundipagg;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

/**
 * Created by raulsvilar on 30/01/17.
 */

public class Utils {

    public static AlertDialog.Builder createAlert(Context context, String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        return dialog;
    }

    public static void changeFragment(FragmentManager manager,
                                      int container, Fragment fragment, boolean withBackStack,
                                      @Nullable String tag) {
        FragmentTransaction transaction = manager.beginTransaction().replace(container, fragment);
        if (withBackStack) transaction.addToBackStack(tag);
        transaction.commit();
    }
}
