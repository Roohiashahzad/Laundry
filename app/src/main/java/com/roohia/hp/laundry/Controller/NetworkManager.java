package com.roohia.hp.laundry.Controller;

import android.content.Context;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.roohia.hp.laundry.gui.interfaces.CallbackInterface;
import com.roohia.hp.laundry.model.constants.Constants;
import com.roohia.hp.laundry.model.constants.ServerUrls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;


public class NetworkManager {
    private static final int REQUEST_TIME_OUT = 60 * 1000;
    private static AsyncHttpClient asyncClient = new AsyncHttpClient();
    private static NetworkManager instance;

    private NetworkManager() {
    }

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
            asyncClient.setResponseTimeout(REQUEST_TIME_OUT);
        }
        return instance;
    }

    public void get(Context context, String url, StringEntity params, JsonHttpResponseHandler responseHandler, CallbackInterface callback) {
        if (!Constants.isApiLive)
            manageOffline(context, url, params, responseHandler, callback);
        else {
            //asyncClient.addHeader(Constants.HEADER_KEY_AUTHORIZATION, Constants.HEADER_KEY_BEARER + PreferenceUtils.getAccessToken(context));
            asyncClient.get(context, getAbsoluteUrl(url), params, "", responseHandler);
        }
    }


    public void post(Context context, String url, StringEntity params, JsonHttpResponseHandler responseHandler) {
        if (!Constants.isApiLive)
            manageOffline(context, url, params, responseHandler, null);
        else {
            //asyncClient.addHeader(Constants.HEADER_KEY_AUTHORIZATION, Constants.HEADER_KEY_BEARER + PreferenceUtils.getAccessToken(context));
            //asyncClient.addHeader(Constants.HEADER_KEY_CONTENT_TYPE, Constants.HEADER_KEY_CONTENT_TYPE_VALUE);
            asyncClient.post(context, getAbsoluteUrl(url), params, "application/json", responseHandler);
        }
    }


    /*public void postLogin(Context context, String url, StringEntity params, JsonHttpResponseHandler responseHandler) {
        if (!Constants.isApiLive)
            manageOffline(context, url, params, responseHandler, null);
        else {
            asyncClient.removeHeader(Constants.HEADER_KEY_AUTHORIZATION);
            asyncClient.addHeader(Constants.HEADER_KEY_CONTENT_TYPE, Constants.HEADER_KEY_CONTENT_TYPE_VALUE_URL_ENCODE);
            asyncClient.post(context, getAbsoluteUrl(url), params, Constants.HEADER_KEY_CONTENT_TYPE_VALUE_URL_ENCODE, responseHandler);
        }
    }*/

    private String getAbsoluteUrl(String relativeUrl) {
        return ServerUrls.BASE_URL + relativeUrl;
    }

    private void manageOffline(final Context context, final String url, final StringEntity params, final JsonHttpResponseHandler responseHandler, final CallbackInterface callback) {

        final String response = DummyDataController.getInstance().getDummyResponse(context, url, params, callback);

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("ResponseStatus").equals("200"))
                responseHandler.onSuccess(200, null, new JSONObject(response));
            else
                responseHandler.onFailure((jsonObject.getInt("ResponseStatus")), null, null, new JSONObject(response));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public InputStream syncHttpGetRequest(Context context, String url) {
        HttpClient httpclient = new DefaultHttpClient();

        try {
            url = url.replaceAll(" ", "%20");
            HttpGet httpget = new HttpGet(url);
            HttpResponse response;
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                InputStream instream = entity.getContent();
                return instream;
            }


        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
