package com.roohia.hp.laundry.gui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.roohia.hp.laundry.R;

public class SplashActivity extends Activity {

    RelativeLayout rlt_splash_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        rlt_splash_logo = (RelativeLayout) findViewById(R.id.rlt_splash_logo);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        rlt_splash_logo.startAnimation(fadeIn);
        fadeIn.setDuration(1500);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setFillAfter(true);
        launchActivity();
    }

    private void launchActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent endSplash = null;
                endSplash = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(endSplash);
                finish();
                overridePendingTransition(R.anim.stay_zoom_in, R.anim.stay_zoom_out);
            }
        }, 2000);
    }
}
