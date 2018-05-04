package com.roohia.hp.laundry.gui.interfaces;

public interface CheckoutInterface extends CallbackInterface {

    public void onCheckoutSuccess();
    public void onCheckoutFailed(String errorCode, String message);
}
