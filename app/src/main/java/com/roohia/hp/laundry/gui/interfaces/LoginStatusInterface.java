package com.roohia.hp.laundry.gui.interfaces;

public interface LoginStatusInterface extends CallbackInterface {

    public void onLoginSuccess();
    public void onLoginFailed(String errorCode, String message);
}
