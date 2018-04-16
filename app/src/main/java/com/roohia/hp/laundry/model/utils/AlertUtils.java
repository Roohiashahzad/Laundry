package com.roohia.hp.laundry.model.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.Toast;
import com.roohia.hp.laundry.R;


public class AlertUtils {
    private static AlertDialog alertDialog = null;

    public static AlertDialog showAlertDialog(Context context, String message, DialogInterface.OnClickListener positiveButtonListener) {
        if (message == null)
            return null;
        try {
            alertDialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, positiveButtonListener)
                    .show();
            alertDialog.setCanceledOnTouchOutside(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return alertDialog;

    }
}
