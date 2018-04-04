package com.roohia.hp.laundry.gui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.interfaces.LoginStatusInterface;
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.viewHolders.LoginViewHolder;

public class LoginActivity extends Activity implements LoginStatusInterface {

    LoginViewHolder loginViewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewHolder = new LoginViewHolder(this);
        loginViewHolder.init(findViewById(R.id.ll_login_root));
        loginViewHolder.setContent();
        loginViewHolder.setLoginCallback(this);
    }

    @Override
    public void onLoginSuccess() {
        loginViewHolder.hideProgressDialog();
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        overridePendingTransition(R.anim.fade_in_slide, R.anim.fade_out_slide);
        this.finish();
    }

    @Override
    public void onLoginFailed(String errorCode, String message) {
        loginViewHolder.hideProgressDialog();
        AlertUtils.showAlertDialog(this, message, null);
    }
}
