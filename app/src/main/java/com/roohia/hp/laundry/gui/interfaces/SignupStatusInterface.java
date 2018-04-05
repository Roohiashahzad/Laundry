package com.roohia.hp.laundry.gui.interfaces;

public interface SignupStatusInterface extends CallbackInterface {

    public void onSignupSuccess();
    public void onSignupFailed(String errorCode, String message);
}
