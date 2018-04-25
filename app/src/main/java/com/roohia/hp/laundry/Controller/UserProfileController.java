package com.roohia.hp.laundry.Controller;

import android.content.Context;
import android.os.AsyncTask;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.roohia.hp.laundry.gui.interfaces.SubmitUserInterface;
import com.roohia.hp.laundry.model.constants.Constants;
import com.roohia.hp.laundry.model.constants.ServerUrls;
import com.roohia.hp.laundry.model.utils.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by mmutaher on 1/10/2018.
 */

public class UserProfileController {

    private static UserProfileController ourInstance = new UserProfileController();

    private UserProfileController() {
    }

    public static UserProfileController getInstance() {
        return ourInstance;
    }

    public void submitUserInfo(final Context context,  String fullname, String email, String address, String contact, final SubmitUserInterface callbackInterface) {

        try {

            JSONObject userInfo = new JSONObject();
            userInfo.put("fullname", fullname );
            userInfo.put("email", email);
            userInfo.put("address", address);
            userInfo.put("contact", contact);
            StringEntity params = new StringEntity(userInfo.toString());
            Constants.isApiLive = false;
            NetworkManager.getInstance().post(context, ServerUrls.UPDATE_USER_URL, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                    Constants.isApiLive = false;
                    super.onSuccess(statusCode, headers, response);
                    try {
                        if (response != null && response.getString("ResponseStatus").equals("200")) {
                            callbackInterface.onSubmissionSuccessful();
                        } else {
                            callbackInterface.onSubmissionFailed(response.getString("ResponseStatus"), CodeUtils.getErrorMessageFromCode(context, response.getString("ResponseStatus")));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Constants.isApiLive = false;
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    callbackInterface.onSubmissionFailed(Constants.NO_INTERNET_ERROR_CODE + "", CodeUtils.getErrorMessageFromCode(context, Constants.NO_INTERNET_ERROR_CODE));
                }
            });
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
