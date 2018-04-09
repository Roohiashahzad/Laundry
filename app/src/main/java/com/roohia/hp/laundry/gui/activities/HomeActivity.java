package com.roohia.hp.laundry.gui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.adapters.NavigationListAdapter;
import com.roohia.hp.laundry.model.bo.NavItem;
import com.roohia.hp.laundry.model.utils.CodeUtils;
import com.roohia.hp.laundry.model.utils.LayoutUtils;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected ActionBar mActionBar = null;
    ListView lvNavItems;
    RelativeLayout rltNewIssue, rltMyIssues, rltSyncData, rltLogout;
    NavigationListAdapter navigationListAdapter;
    NavigationView navView = null;
    private ArrayList<NavItem> navItems = new ArrayList<>();
    private ProgressDialog progressDialog;

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


    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onNavItemSelection(position);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void onNavItemSelection(int position) {


    }

    public void handleGridAndNavActions(int position) {

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


}
