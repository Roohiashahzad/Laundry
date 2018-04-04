package com.roohia.hp.laundry.model.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CodeUtils {
    private static CodeUtils ourInstance = new CodeUtils();

    private CodeUtils() {
    }

    public static CodeUtils getInstance() {
        return ourInstance;
    }

    public static String getFileNameFromPath(String itemPath) {
        if (itemPath == null)
            return "";
        int index = itemPath.lastIndexOf('/');
        if (index > -1)
            return itemPath.substring(index + 1);
        else
            return itemPath;
    }

    public static String removeNullFromString(String txt) {
        if (txt == null) {
            return "";
        }
        return txt;

    }

    public static String getLoginErrorMessageFromCode(Context context, String code) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier("login_response_code_" + code, "string", packageName);
        return context.getString(resId);
    }

    public static String getErrorMessageFromCode(Context context, String code) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier("response_code_" + code, "string", packageName);
        return context.getString(resId);
    }

    public static String getErrorMessageFromCode(Context context, String code, String[] param) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier("response_code_" + code, "string", packageName);
        return context.getString(resId, param);
    }

    public static String getErrorMessageFromCode(Context context, int code) {
        return getErrorMessageFromCode(context, code + "");
    }

    public static String getLoginErrorMessageFromCode(Context context, int code) {
        return getLoginErrorMessageFromCode(context, code + "");
    }

    public static String getErrorMessageFromCode(Context context, int code, String params[]) {
        return getErrorMessageFromCode(context, code + "", params);
    }

    public static String getLocalPath(String path) {
        if (path != null && !path.isEmpty()) {
            if (path.contains("http")) {
                return path.split("http")[0];
            } else
                return path;
        }
        return "";
    }

    public static String getHttpPath(String path) {
        if (path != null && !path.isEmpty()) {
            if (path.contains("http")) {
                return "http" + path.split("http")[1];
            }
        }
        return "";
    }


    public List convertArrayToList(Object[] array) {
        List list = new ArrayList();
        for (Object object : array)
            list.add(object);
        return list;
    }

    public Object[] convertListArray(List list) {
        Object[] array = new Object[list.size()];
        for (int i = 0; i < list.size(); i++)
            array[i] = list.get(i);
        return array;
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

    public byte[] convertBitmapToByteArray(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }
}
