package com.roohia.hp.laundry.gui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.activities.HomeActivity;
import com.roohia.hp.laundry.gui.adapters.NewOrderItemsAdapter;
import com.roohia.hp.laundry.model.bo.OrderItem;

import java.util.ArrayList;

public class NewOrderFragment extends Fragment implements  AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView lvOrderItems = null;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private NewOrderItemsAdapter newOrderItemsAdapter = null;


    public NewOrderFragment() {
    }

    public static NewOrderFragment newInstance() {
        Bundle args = new Bundle();
        NewOrderFragment fragment = new NewOrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_new_order_fragments, container, false);
        LinearLayout root = (LinearLayout) view.findViewById(R.id.root);
        root.setOnClickListener(this);
        lvOrderItems = (ListView) view.findViewById(R.id.new_order_items);
        newOrderItemsAdapter = new NewOrderItemsAdapter(getContext(), orderItems);
        lvOrderItems.setAdapter(newOrderItemsAdapter);
        lvOrderItems.setOnItemClickListener(this);
        getAllOrderItems();
        return view;
    }


    private void getAllOrderItems() {
        ((HomeActivity) getActivity()).showProgressDialog("Loading...");
        orderItems.add(new OrderItem("Shirt",R.drawable.icon_shirt));
        orderItems.add(new OrderItem("Trousers",R.drawable.icon_trousers));
        orderItems.add(new OrderItem("Dress",R.drawable.icon_dress));
        orderItems.add(new OrderItem("Frock",R.drawable.icon_frocks));
        orderItems.add(new OrderItem("Jacket",R.drawable.icon_jacket));
        orderItems.add(new OrderItem("Jeans",R.drawable.icon_jeans));
        orderItems.add(new OrderItem("Sweater",R.drawable.icon_sweater));
        orderItems.add(new OrderItem("Suit",R.drawable.icon_suits));
        orderItems.add(new OrderItem("Traditional",R.drawable.icon_traditional));
        orderItems.add(new OrderItem("Shorts",R.drawable.icon_shorts));
        orderItems.add(new OrderItem("Undergarments",R.drawable.icon_undergarments));
        newOrderItemsAdapter.notifyDataSetChanged();

        ((HomeActivity) getActivity()).hideProgressDialog();

    }







    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       /* lvTableItems.setOnItemClickListener(null);
        //if (srRefresh.isRefreshing()) {
        ((HomeActivity) getActivity()).showFragment(IssueDetailsFragment.newInstance(getContext(), tableAdapter.getItems().get(i)), "ISSUE_DETAILS");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lvTableItems.setOnItemClickListener(NewOrderFragment.this);
            }
        }, 2000);
        //}*/
    }

    @Override
    public void onClick(View view) {

    }
}
