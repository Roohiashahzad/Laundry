package com.roohia.hp.laundry.Controller;

import android.content.Context;
import android.os.AsyncTask;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.interfaces.SubmitUserInterface;
import com.roohia.hp.laundry.model.constants.Constants;
import com.roohia.hp.laundry.model.constants.ServerUrls;
import com.roohia.hp.laundry.model.database.DBHandler;
import com.roohia.hp.laundry.model.dbo.User;
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

    public void submitUserInfo(final Context context, String username, final String fullname, final String email, final String address, final String contact, final SubmitUserInterface callbackInterface) {

        try {
            final User user = DBHandler.getInstance().getCurrentUser();
            JSONObject userInfo = new JSONObject();
            userInfo.put("id", user.getId());
            userInfo.put("username", username);
            userInfo.put("email", email);
            StringEntity params = new StringEntity(userInfo.toString());
            Constants.isApiLive = true;
            NetworkManager.getInstance().post(context, ServerUrls.UPDATE_USER_URL, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                    Constants.isApiLive = false;
                    super.onSuccess(statusCode, headers, response);
                    try {
                        if (response != null && response.getString("ResponseStatus").equals("200")) {
                            user.setAddress(address);
                            user.setContact(contact);
                            user.setFullName(fullname);
                            user.save();
                            callbackInterface.onSubmissionSuccessful();
                        } else {
                            callbackInterface.onSubmissionFailed(response.getString("ResponseStatus"),response.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Constants.isApiLive = false;
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    callbackInterface.onSubmissionFailed(statusCode + "", context.getString(R.string.unexpected_error_on_server));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                    Constants.isApiLive = false;
                    super.onFailure(statusCode, headers, error, throwable);
                    try {
                        callbackInterface.onSubmissionFailed(statusCode + "",context.getString(R.string.unexpected_error_on_server));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
