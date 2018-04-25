package com.roohia.hp.laundry.Controller;

import android.content.Context;

import com.roohia.hp.laundry.gui.interfaces.CallbackInterface;
import com.roohia.hp.laundry.model.constants.ServerUrls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.entity.StringEntity;

public class DummyDataController {
    private static DummyDataController ourInstance = new DummyDataController();

    private DummyDataController() {
    }

    public static DummyDataController getInstance() {
        return ourInstance;
    }

    public String login(Context context, StringEntity params) {
        try {
            JSONObject jsonObject = new JSONObject(convertStreamToString(params.getContent()));
            if (jsonObject.getString("username").equals("admin") && jsonObject.getString("password").equals("admin")) {
                return FileReader.getInstance().readFileFromAssets(context, "api/login.json");
            } else {
                return FileReader.getInstance().readFileFromAssets(context, "api/login_failure.json");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String signup(Context context, StringEntity params) {
        try {
            JSONObject jsonObject = new JSONObject(convertStreamToString(params.getContent()));
            return FileReader.getInstance().readFileFromAssets(context, "api/signup.json");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String profile(Context context, StringEntity params) {
        try {
            JSONObject jsonObject = new JSONObject(convertStreamToString(params.getContent()));
            return FileReader.getInstance().readFileFromAssets(context, "api/profile.json");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



    public String getDummyResponse(Context context, String url, StringEntity params, CallbackInterface callback) {
        switch (url) {
            case ServerUrls.LOGIN_URL:
                return login(context, params);

            case ServerUrls.SIGNUP_URL:
                return signup(context,params);

            case ServerUrls.UPDATE_USER_URL:
                return profile(context, params);
        }
        return "";
    }


}
