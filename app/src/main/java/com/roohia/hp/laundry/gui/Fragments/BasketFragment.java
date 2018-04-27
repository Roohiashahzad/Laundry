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
import android.widget.RelativeLayout;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.activities.HomeActivity;
import com.roohia.hp.laundry.gui.adapters.BasketItemsAdapter;
import com.roohia.hp.laundry.gui.adapters.NewOrderItemsAdapter;
import com.roohia.hp.laundry.model.bo.NewOrderItem;

import java.util.ArrayList;

public class BasketFragment extends Fragment implements  AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView lvOrderItems = null;
    private ArrayList<NewOrderItem> BasketItems = new ArrayList<>();
    private BasketItemsAdapter basketItemsAdapter = null;


    public BasketFragment() {
    }

    public static BasketFragment newInstance() {
        Bundle args = new Bundle();
        BasketFragment fragment = new BasketFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_basket, container, false);
        RelativeLayout root = (RelativeLayout) view.findViewById(R.id.root);
        root.setOnClickListener(this);
        lvOrderItems = (ListView) view.findViewById(R.id.order_items);
        basketItemsAdapter = new BasketItemsAdapter(getContext(), BasketItems,this);
        lvOrderItems.setAdapter(basketItemsAdapter);
        lvOrderItems.setOnItemClickListener(this);
        getAllOrderItems();
        return view;
    }


    private void getAllOrderItems() {
        ((HomeActivity) getActivity()).showProgressDialog("Loading...");
        BasketItems.add(new NewOrderItem("Shirt",R.drawable.icon_shirt,1,0));
        BasketItems.add(new NewOrderItem("Dress",R.drawable.icon_dress,0,1));
        BasketItems.add(new NewOrderItem("Jeans",R.drawable.icon_jeans,0,2));
        BasketItems.add(new NewOrderItem("Suit",R.drawable.icon_suits,0,1));
        basketItemsAdapter.notifyDataSetChanged();
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
        switch (view.getId()){
            case R.id.btn_remove:
                if(basketItemsAdapter != null) {
                    basketItemsAdapter.getItems().remove(view.getTag());
                    basketItemsAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
