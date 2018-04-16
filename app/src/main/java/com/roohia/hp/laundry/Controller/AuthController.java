package com.roohia.hp.laundry.Controller;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.roohia.hp.laundry.gui.interfaces.LoginStatusInterface;
import com.roohia.hp.laundry.gui.interfaces.SignupStatusInterface;
import com.roohia.hp.laundry.model.constants.Constants;
import com.roohia.hp.laundry.model.constants.ServerUrls;
import com.roohia.hp.laundry.model.utils.CodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class AuthController {
    private static AuthController ourInstance = new AuthController();

    private AuthController() {
    }

    public static AuthController getInstance() {
        return ourInstance;
    }

    public void login(final Context context, final String username, final String password, final LoginStatusInterface loginStatusInterface) {
        if (!CodeUtils.getInstance().checkNetworkConnectivity(context)) {
            loginStatusInterface.onLoginFailed(Constants.NO_INTERNET_ERROR_CODE + "", CodeUtils.getErrorMessageFromCode(context, Constants.NO_INTERNET_ERROR_CODE));
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            final StringEntity params = new StringEntity(jsonObject.toString());

            Constants.isApiLive = false;
            NetworkManager.getInstance().post(context, ServerUrls.LOGIN_URL, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        if (response != null && response.getString("ResponseStatus").equals("200")) {
                            Constants.isApiLive = false;

                                loginStatusInterface.onLoginSuccess();
                                return;

                        } else {
                            Constants.isApiLive = false;
                            loginStatusInterface.onLoginFailed(response.getString("ResponseStatus"), response.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Constants.isApiLive = false;
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    try {
                        loginStatusInterface.onLoginFailed(statusCode + "",errorResponse.getString("message") );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signup(final Context context, final String username, final String fullname, final String address, final String contactnumber, final String email, final String password, final SignupStatusInterface signupStatusInterface) {
        if (!CodeUtils.getInstance().checkNetworkConnectivity(context)) {
            signupStatusInterface.onSignupFailed(Constants.NO_INTERNET_ERROR_CODE + "", CodeUtils.getErrorMessageFromCode(context, Constants.NO_INTERNET_ERROR_CODE));
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("fullname", fullname);
            jsonObject.put("address", address);
            jsonObject.put("contact", contactnumber);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            final StringEntity params = new StringEntity(jsonObject.toString());

            Constants.isApiLive = false;
            NetworkManager.getInstance().post(context, ServerUrls.SIGNUP_URL, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        if (response != null && response.getString("ResponseStatus").equals("200")) {
                            Constants.isApiLive = false;
                            signupStatusInterface.onSignupSuccess();
                            return;
                        } else {
                            Constants.isApiLive = false;
                            signupStatusInterface.onSignupFailed(response.getString("ResponseStatus"), response.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Constants.isApiLive = false;
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    try {
                        signupStatusInterface.onSignupFailed(statusCode + "",errorResponse.getString("message") );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
