package com.roohia.hp.laundry.viewHolders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.roohia.hp.laundry.Controller.AuthController;
import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.activities.LoginActivity;
import com.roohia.hp.laundry.gui.activities.SignupActivity;

import com.roohia.hp.laundry.gui.interfaces.SignupStatusInterface;
import com.roohia.hp.laundry.model.utils.CodeUtils;

public class SignupViewHolder implements View.OnClickListener{
    private EditText etUsername = null;
    private EditText etFullname = null;
    private EditText etAddress = null;
    private EditText etContact = null;
    private EditText etEmail = null;
    private EditText etPassword = null;
    private EditText etConfirmPassword = null;
    private Button btnSignup = null;
    ProgressDialog progressDialog = null;
    private Context mContext ;
    private View root;
    private SignupStatusInterface signupCallback;

    public SignupViewHolder(Context pContext) {
        this.mContext = pContext;
    }

    public void init(View view) {
        this.root = view;
        ScrollView rltSignupContent = (ScrollView) root.findViewById(R.id.sv_content_signup);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        rltSignupContent.startAnimation(fadeIn);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(1500);
        fadeIn.setFillAfter(true);
        etUsername = (EditText) root.findViewById(R.id.et_username);
        etFullname = (EditText) root.findViewById(R.id.et_Fullname);
        etAddress = (EditText) root.findViewById(R.id.et_Address);
        etContact = (EditText) root.findViewById(R.id.et_contact_number);
        etEmail = (EditText) root.findViewById(R.id.et_Email);
        etPassword = (EditText) root.findViewById(R.id.et_password);
        etConfirmPassword = (EditText) root.findViewById(R.id.et_confirm_password);
        btnSignup = (Button) root.findViewById(R.id.btn_signup);
    }
    public void setContent(){

        btnSignup.setOnClickListener(this);
        etConfirmPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    signup();
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                signup();
                break;
        }

    }

    public boolean validate()
    {
        if (etUsername.getText().toString().isEmpty()){
            Toast.makeText(mContext,"Please enter your username to signup!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etFullname.getText().toString().isEmpty()){
            Toast.makeText(mContext,"Please enter your full name to signup!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etAddress.getText().toString().isEmpty()){
            Toast.makeText(mContext,"Please enter your address to signup!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etContact.getText().toString().isEmpty()){
            Toast.makeText(mContext,"Please enter your contact number to signup!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etEmail.getText().toString().isEmpty()){
            Toast.makeText(mContext,"Please enter your email address to signup!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPassword.getText().toString().isEmpty()){
            Toast.makeText(mContext,"Please enter a valid password to signup!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etConfirmPassword.getText().toString().isEmpty()){
            Toast.makeText(mContext,"Please re-enter your password to signup!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())))
        {
            Toast.makeText(mContext,"Entered passwords don't match!", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }

    public void signup() {
        if (validate()) {
            CodeUtils.getInstance().hideKeyboard((Activity) mContext);
            showProgressDialog("Signing you up... ");
            AuthController.getInstance().signup(mContext, etUsername.getText().toString(),etFullname.getText().toString() ,etAddress.getText().toString(),etContact.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString(), signupCallback);
        }
    }
    public void showProgressDialog(String message) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(mContext,R.style.AppCompatAlertDialogStyle);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public SignupStatusInterface getSignupCallback() {
        return signupCallback;
    }

    public void setSignupCallback(SignupStatusInterface loginCallback) {
        this.signupCallback = loginCallback;
    }
}
