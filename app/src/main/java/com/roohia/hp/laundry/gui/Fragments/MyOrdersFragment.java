package com.roohia.hp.laundry.gui.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.activities.HomeActivity;
import com.roohia.hp.laundry.gui.adapters.MyOrdersAdapter;
import com.roohia.hp.laundry.model.bo.MyOrderItem;

import java.util.ArrayList;

public class MyOrdersFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    RelativeLayout rltNoOrders;
    LinearLayout llOrdersGrid;

    private ListView lvTableItems = null;
    private ArrayList<MyOrderItem> myOrders = new ArrayList<>();
    private MyOrdersAdapter tableAdapter = null;


    public MyOrdersFragment() {
    }

    public static MyOrdersFragment newInstance() {
        Bundle args = new Bundle();
        MyOrdersFragment fragment = new MyOrdersFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_my_orders_fragment, container, false);
        RelativeLayout root = (RelativeLayout) view.findViewById(R.id.root);
        root.setOnClickListener(this);
        lvTableItems = (ListView) view.findViewById(R.id.table_all_orders);
        tableAdapter = new MyOrdersAdapter(getContext(), myOrders);
        lvTableItems.setAdapter(tableAdapter);
        lvTableItems.setOnItemClickListener(this);
        llOrdersGrid = (LinearLayout) root.findViewById(R.id.ll_orders_grid);
        rltNoOrders = (RelativeLayout) root.findViewById(R.id.rlt_no_orders);


        getAllOrders();
        return view;
    }


    private void getAllOrders() {
        ((HomeActivity) getActivity()).showProgressDialog("Loading...");
        myOrders.add(new MyOrderItem("1","18-APRIL-2018","In-Process","OrderRecord Pickup Time: 20-APRIL-2018 10:00:00"));
        myOrders.add(new MyOrderItem("2","18-APRIL-2018","In-Process","OrderRecord Pickup Time: 20-APRIL-2018 10:00:00"));
        myOrders.add(new MyOrderItem("3","18-APRIL-2018","In-Process","OrderRecord Pickup Time: 20-APRIL-2018 10:00:00"));
        myOrders.add(new MyOrderItem("4","18-APRIL-2018","In-Process","OrderRecord Pickup Time: 20-APRIL-2018 10:00:00"));
        ((HomeActivity) getActivity()).hideProgressDialog();

    }






    @Override
    public void onClick(View view) {

    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       /* lvTableItems.setOnItemClickListener(null);
        //if (srRefresh.isRefreshing()) {
        ((HomeActivity) getActivity()).showFragment(IssueDetailsFragment.newInstance(getContext(), tableAdapter.getItems().get(i)), "ISSUE_DETAILS");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lvTableItems.setOnItemClickListener(MyOrdersFragment.this);
            }
        }, 2000);
        //}*/
    }

}
