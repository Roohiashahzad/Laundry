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
import com.roohia.hp.laundry.model.bo.NewOrderItem;

import java.util.ArrayList;

public class NewOrderFragment extends Fragment implements  AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView lvOrderItems = null;
    private ArrayList<NewOrderItem> newOrderItems = new ArrayList<>();
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
        newOrderItemsAdapter = new NewOrderItemsAdapter(getContext(), newOrderItems);
        lvOrderItems.setAdapter(newOrderItemsAdapter);
        lvOrderItems.setOnItemClickListener(this);
        getAllOrderItems();
        return view;
    }


    private void getAllOrderItems() {
        ((HomeActivity) getActivity()).showProgressDialog("Loading...");
        newOrderItems.add(new NewOrderItem("Shirt",R.drawable.icon_shirt));
        newOrderItems.add(new NewOrderItem("Trousers",R.drawable.icon_trousers));
        newOrderItems.add(new NewOrderItem("Dress",R.drawable.icon_dress));
        newOrderItems.add(new NewOrderItem("Frock",R.drawable.icon_frocks));
        newOrderItems.add(new NewOrderItem("Jacket",R.drawable.icon_jacket));
        newOrderItems.add(new NewOrderItem("Jeans",R.drawable.icon_jeans));
        newOrderItems.add(new NewOrderItem("Sweater",R.drawable.icon_sweater));
        newOrderItems.add(new NewOrderItem("Suit",R.drawable.icon_suits));
        newOrderItems.add(new NewOrderItem("Traditional",R.drawable.icon_traditional));
        newOrderItems.add(new NewOrderItem("Shorts",R.drawable.icon_shorts));
        newOrderItems.add(new NewOrderItem("Undergarments",R.drawable.icon_undergarments));
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
