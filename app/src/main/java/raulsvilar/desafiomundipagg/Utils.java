package raulsvilar.desafiomundipagg;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by raulsvilar on 30/01/17.
 */

public class Utils {

    public static void showAlert(Context context, String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }
}
