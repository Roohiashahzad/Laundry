package com.roohia.hp.laundry.gui.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.Fragments.BasketFragment;
import com.roohia.hp.laundry.gui.Fragments.MyOrdersFragment;
import com.roohia.hp.laundry.gui.Fragments.NewOrderFragment;
import com.roohia.hp.laundry.gui.Fragments.UserProfileFragment;
import com.roohia.hp.laundry.gui.adapters.NavigationListAdapter;
import com.roohia.hp.laundry.model.bo.NavItem;
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.model.utils.LayoutUtils;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnTouchListener {

    protected ActionBar mActionBar = null;
    ListView lvNavItems;
    RelativeLayout rltNewOrder, rltMyOrders, rltUserProfile, rltLogout, rltBasket, rltScanQR;
    NavigationListAdapter navigationListAdapter;
    NavigationView navView = null;
    private ArrayList<NavItem> navItems = new ArrayList<>();
    private ProgressDialog progressDialog;

    ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        setActionBarTitle(getString(R.string.title_activity_main));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        initializeNavigationMenuItems();
        lvNavItems = (ListView) findViewById(R.id.lst_menu_items);
        navigationListAdapter = new NavigationListAdapter(this, navItems);
        lvNavItems.setAdapter(navigationListAdapter);
        lvNavItems.setOnItemClickListener(this);
        setDrawerWidth();

        rltNewOrder = (RelativeLayout) findViewById(R.id.rlt_log_new_order);
        rltMyOrders = (RelativeLayout) findViewById(R.id.rlt_my_logged_orders);
        rltUserProfile = (RelativeLayout) findViewById(R.id.rlt_user_profile);
        rltLogout = (RelativeLayout) findViewById(R.id.rlt_logout);
        rltBasket = (RelativeLayout) findViewById(R.id.rlt_basket);
        rltScanQR = (RelativeLayout) findViewById(R.id.rlt_scan_qr);


        rltNewOrder.setOnTouchListener(this);
        rltMyOrders.setOnTouchListener(this);
        rltUserProfile.setOnTouchListener(this);
        rltLogout.setOnTouchListener(this);
        rltBasket.setOnTouchListener(this);
        rltScanQR.setOnTouchListener(this);

    }



    public void setDrawerWidth() {
        android.support.v4.widget.DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) navView.getLayoutParams();
        params.width = (int) ((LayoutUtils.getDeviceWidth(HomeActivity.this)) * 0.80);
        navView.setLayoutParams(params);
    }

    public void setActionBarTitle(String title) {
        if (mActionBar != null)
            mActionBar.setTitle(title);
    }

    private void initializeNavigationMenuItems() {
        navItems.add(new NavItem("Home", R.drawable.icon_home, true));
        navItems.add(new NavItem("New Order", R.drawable.icon_log_new_issue, false));
        navItems.add(new NavItem("My Orders", R.drawable.icon_view_my_logged_issues, false));
        navItems.add(new NavItem("Logout", R.drawable.icon_logout, false));

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onNavItemSelection(position);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void onNavItemSelection(int position) {
        if (navigationListAdapter != null) {
            navigationListAdapter.getItems().get(0).setSelected(false);
            navigationListAdapter.getItems().get(1).setSelected(false);
            navigationListAdapter.getItems().get(2).setSelected(false);
            navigationListAdapter.getItems().get(3).setSelected(false);

            navigationListAdapter.getItems().get(position).setSelected(true);

            navigationListAdapter.notifyDataSetChanged();

            if (position == 3)
                handleGridAndNavActions(4);
            else
                handleGridAndNavActions(position);
        }

    }

    public void handleGridAndNavActions(int position) {
        switch (position) {
            case 0:
                clearBackStack();
                break;
            case 1:
                showFragment(NewOrderFragment.newInstance(), "newOrder");
                break;
            case 2:
                showFragment(MyOrdersFragment.newInstance(), "allOrders");
                break;
            case 3:
                showFragment(UserProfileFragment.newInstance(this),"userProfile");
                break;
            case 4:
                handleLogout();
                break;
            case 5:
                showFragment(BasketFragment.newInstance(),"basket");
                break;
        }
    }

    public void showFragment(Fragment fragment, String tag) {
        if (fragment == null)
            return;
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.ll_main_content, fragment, tag);
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_out_left, R.anim.slide_out_right);
            fragmentTransaction.show(fragment);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }


    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        RelativeLayout pressedButton = null;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (v.getId() == R.id.rlt_log_new_order) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_log_new_order);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid_pressed));
                    return true;

                } else if (v.getId() == R.id.rlt_my_logged_orders) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_my_logged_orders);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid_pressed));
                    return true;

                } else if (v.getId() == R.id.rlt_user_profile) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_user_profile);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid_pressed));
                    return true;
                } else if (v.getId() == R.id.rlt_logout) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_logout);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid_pressed));
                    return true;
                } else if (v.getId() == R.id.rlt_basket) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_basket);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid_pressed));
                    return true;

                } else if (v.getId() == R.id.rlt_scan_qr) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_scan_qr);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid_pressed));
                    return true;

                }
                else {
                    return false;
                }


            case MotionEvent.ACTION_UP: {
                if (v.getId() == R.id.rlt_log_new_order) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_log_new_order);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid));

                    onNavItemSelection(1);

                    return true;

                } else if (v.getId() == R.id.rlt_my_logged_orders) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_my_logged_orders);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid));

                    onNavItemSelection(2);

                    return true;

                } else if (v.getId() == R.id.rlt_user_profile) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_user_profile);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid));

                    handleGridAndNavActions(3);

                    return true;

                } else if (v.getId() == R.id.rlt_logout) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_logout);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid));

                    handleGridAndNavActions(4);

                    return true;
                } else if (v.getId() == R.id.rlt_basket) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_basket);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid));

                    handleGridAndNavActions(5);

                    return true;
                }
                else if (v.getId() == R.id.rlt_scan_qr) {

                    pressedButton = (RelativeLayout) findViewById(R.id.rlt_scan_qr);
                    pressedButton.setBackground(ContextCompat.getDrawable(HomeActivity.this, R.drawable.border_round_corners_home_grid));

                    IntentIntegrator integrator = new IntentIntegrator(this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan the QR code to continue.");
                    integrator.initiateScan();
                    return true;
                }


                else {
                    return false;
                }
            }


        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                AlertUtils.showAlertDialog(this,"No data found for this QR code.",null);
            } else {
                AlertUtils.showAlertDialog(this,"This order belongs to: \n" + result.getContents(),null);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void clearBackStack() {
        FragmentManager fm = this.getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public void removeTopFragmentFromBackStack(){
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack();
    }

    public void handleLogout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }


}
