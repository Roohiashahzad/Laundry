package com.roohia.hp.laundry.model.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;


public class LayoutUtils {

    public static int getDeviceWidth(Activity context) {
        if (context == null)
            return 0;
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int dWidth = dm.widthPixels;
        return (int) dWidth;
    }

}
