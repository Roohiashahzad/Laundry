package com.roohia.hp.laundry.Controller;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.interfaces.CheckoutInterface;
import com.roohia.hp.laundry.model.constants.Constants;
import com.roohia.hp.laundry.model.constants.ServerUrls;
import com.roohia.hp.laundry.model.database.DBHandler;
import com.roohia.hp.laundry.model.dbo.OrderItems;
import com.roohia.hp.laundry.model.utils.CodeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class CheckoutController {
    private static CheckoutController ourInstance = new CheckoutController();

    private CheckoutController() {
    }

    public static CheckoutController getInstance() {
        return ourInstance;
    }

    public void checkout(final Context context, List<OrderItems> orderItems, String orderId, String userId, final CheckoutInterface checkoutInterface) {
        if (!CodeUtils.getInstance().checkNetworkConnectivity(context)) {
            checkoutInterface.onCheckoutFailed(Constants.NO_INTERNET_ERROR_CODE + "", CodeUtils.getErrorMessageFromCode(context, Constants.NO_INTERNET_ERROR_CODE));
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderId",orderId);
            jsonObject.put("userid", userId);

            JSONArray jsonArray = new JSONArray();

            for(OrderItems item: orderItems){
                JSONObject itemObj= new JSONObject();
                itemObj.put("orderId",item.getOrderId());
                itemObj.put("orderDetailsId", item.getOrderDetailsId());
                itemObj.put("itemName",item.getItemName());
                itemObj.put("itemPressCount",item.getItemPressCount());
                itemObj.put("itemWashCount",item.getItemWashCount());

                jsonArray.put(itemObj);

            }
            jsonObject.put("orderitems",jsonArray);
            final StringEntity params = new StringEntity(jsonObject.toString());

            Constants.isApiLive = true;
            NetworkManager.getInstance().post(context, ServerUrls.CHECKOUT_URL, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        if (response != null && response.getString("ResponseStatus").equals("200")) {
                            Constants.isApiLive = false;
                            DBHandler.getInstance().finalizeCurrentOrder();
                            checkoutInterface.onCheckoutSuccess();
                            return;

                        } else {
                            Constants.isApiLive = false;
                            checkoutInterface.onCheckoutFailed(response.getString("ResponseStatus"), response.getString("message"));
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
                        checkoutInterface.onCheckoutFailed(statusCode + "",context.getString(R.string.unexpected_error_on_server));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                    Constants.isApiLive = false;
                    super.onFailure(statusCode, headers, error, throwable);
                    try {
                        checkoutInterface.onCheckoutFailed(statusCode + "",context.getString(R.string.unexpected_error_on_server));
                    } catch (Exception e) {
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
