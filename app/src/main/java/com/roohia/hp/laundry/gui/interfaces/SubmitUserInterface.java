package com.roohia.hp.laundry.gui.interfaces;



public interface SubmitUserInterface extends CallbackInterface {
    public void onSubmissionSuccessful();

    public void onSubmissionFailed(String code, String message);
}
