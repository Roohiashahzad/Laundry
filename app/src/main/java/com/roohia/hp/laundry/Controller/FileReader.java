package com.roohia.hp.laundry.Controller;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


import com.roohia.hp.laundry.gui.application.LaundraGoApplication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FileReader {
    private static FileReader ourInstance = new FileReader();
    private String TAG = "AssetsFileReader";

    private FileReader() {
    }

    public static FileReader getInstance() {
        return ourInstance;
    }

    public String readFileFromAssets(Context context, String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e(TAG, "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing asset " + name);
                }
            }
        }

        return null;
    }

    /*public void writeToFile(Context context, String name, String string) {
        PrintWriter out = null;
        try {
            FileWriter fileWriter = new FileWriter(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/" + name));
            out = new PrintWriter(fileWriter);
            out.println(string);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public byte[] readImageFromStorage(String path) {
        File file = new File(path);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public String saveImageToFile(Context context, String fileName, Bitmap image) {
        OutputStream fOut = null;

        //String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File path = LaundraGoApplication.getInstance().getExternalFilesDir(null);
        try {

            File imageFile = new File(path, fileName);
            fOut = new FileOutputStream(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            image.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path.getAbsolutePath() + "/" + fileName;
    }

    public String saveSignatureImageToFile(Context context, String fileName, Bitmap image) {
        OutputStream fOut = null;

        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        try {

            File imageFile = new File(path);
            fOut = new FileOutputStream(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public Bitmap loadImageFromStorage(String path) {

        try {
            File f = new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void deleteFile(String path) {
        File fdelete = new File(path);
        if (fdelete.exists()) {
            fdelete.delete();
        }
    }*/
}
