package com.roohia.hp.laundry.gui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.interfaces.SignupStatusInterface;
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.viewHolders.SignupViewHolder;

public class SignupActivity extends AppCompatActivity implements SignupStatusInterface {

    SignupViewHolder signupViewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupViewHolder = new SignupViewHolder(this);
        signupViewHolder.init(findViewById(R.id.sv_content_signup));
        signupViewHolder.setContent();
        signupViewHolder.setSignupCallback(this);
    }

    @Override
    public void onSignupSuccess() {
        signupViewHolder.hideProgressDialog();
        Intent LoginIntent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
        overridePendingTransition(R.anim.fade_in_slide, R.anim.fade_out_slide);
        this.finish();
    }

    @Override
    public void onSignupFailed(String errorCode, String message) {
        signupViewHolder.hideProgressDialog();
        AlertUtils.showAlertDialog(this, message, null);
    }
}
