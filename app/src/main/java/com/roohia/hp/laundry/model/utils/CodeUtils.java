package com.roohia.hp.laundry.model.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class CodeUtils {
    private static CodeUtils ourInstance = new CodeUtils();

    private CodeUtils() {
    }

    public static CodeUtils getInstance() {
        return ourInstance;
    }

    public static String getErrorMessageFromCode(Context context, String code) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier("response_code_" + code, "string", packageName);
        return context.getString(resId);
    }

    public static String getErrorMessageFromCode(Context context, int code) {
        return getErrorMessageFromCode(context, code + "");
    }

    public void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean checkNetworkConnectivity(Context context){
        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null &&activeNetwork.isConnected());
    }

    public int convertStringToInt(String inputstring){
        if(inputstring == null || inputstring.isEmpty()){
            return  0;
        }
        else
        {
            return Integer.valueOf(inputstring);
        }
    }

}
