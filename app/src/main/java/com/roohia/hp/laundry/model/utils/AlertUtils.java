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

    public static ProgressDialog showProgressDialog(Context context, String title, String message) {
        if (context != null) {
            final ProgressDialog progressDialog = ProgressDialog.show(context, "", message, true);
            progressDialog.setCancelable(false);
            return progressDialog;
        }
        return null;
    }

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

    public static void showAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveButtonListener) {
        alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton(android.R.string.yes, positiveButtonListener)
                .show();
        alertDialog.setCanceledOnTouchOutside(false);

    }

    public static void showAlertDialog(Context context, String title, String message, String pbText, DialogInterface.OnClickListener positiveButtonListener) {
        alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton(pbText, positiveButtonListener)
                .show();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

    }
    /*public static void showAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveButtonListener) {
        alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton(android.R.string.yes, positiveButtonListener)
                .show();
        alertDialog.setCanceledOnTouchOutside(false);

    }*/

    public static void showConfirmationDialog(Context context, String message, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("Yes", positiveButtonListener)
                .setNegativeButton("No", negativeButtonListener)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public static void showConfirmationDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("Yes", positiveButtonListener)
                .setNegativeButton("No", negativeButtonListener)
                .show();
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public static void showConfirmationDialog(Context context, String title, String message, String pbTex, String nbText, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(pbTex, positiveButtonListener)
                .setNegativeButton(nbText, negativeButtonListener)
                .show();
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public static void showToast(Context mContext, String message) {
        Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 35, 400);
        toast.show();
    }
}
