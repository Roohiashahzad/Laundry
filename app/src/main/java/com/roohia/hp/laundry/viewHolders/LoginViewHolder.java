package com.roohia.hp.laundry.viewHolders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.roohia.hp.laundry.Controller.AuthController;
import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.interfaces.LoginStatusInterface;
import com.roohia.hp.laundry.model.constants.Constants;
import com.roohia.hp.laundry.model.utils.CodeUtils;

public class LoginViewHolder implements View.OnClickListener{
    private EditText etUsername = null;
    private EditText etPassword = null;
    private Button btnLogin = null;
    ProgressDialog progressDialog = null;
    private Context mContext ;
    private View root;
    private LoginStatusInterface loginCallback;

    public LoginViewHolder(Context pContext) {
        this.mContext = pContext;
    }

    public void init(View view) {
        this.root = view;
        RelativeLayout rltLoginContent = (RelativeLayout) root.findViewById(R.id.rlt_login_content);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        rltLoginContent.startAnimation(fadeIn);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(1500);
        fadeIn.setFillAfter(true);
        etUsername = (EditText) root.findViewById(R.id.et_username);
        etPassword = (EditText) root.findViewById(R.id.et_password);
        btnLogin = (Button) root.findViewById(R.id.btn_login);
    }
    public void setContent(){
        btnLogin.setOnClickListener(this);
        etPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    authenticateLogin();
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                authenticateLogin();
                break;
        }

    }
    public void authenticateLogin() {
        String userName = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (userName.isEmpty()) {
            Toast.makeText(mContext, "Invalid Username!", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(mContext, "Invalid Password!", Toast.LENGTH_LONG).show();
            return;
        }
        CodeUtils.getInstance().hideKeyboard((Activity) mContext);
        showProgressDialog("Authenticating... ");

        AuthController.getInstance().login(mContext, userName, password, loginCallback);
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

    public LoginStatusInterface getLoginCallback() {
        return loginCallback;
    }

    public void setLoginCallback(LoginStatusInterface loginCallback) {
        this.loginCallback = loginCallback;
    }
}
