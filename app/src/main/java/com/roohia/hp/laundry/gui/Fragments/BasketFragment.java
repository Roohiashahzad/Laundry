package com.roohia.hp.laundry.gui.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.roohia.hp.laundry.Controller.CheckoutController;
import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.activities.HomeActivity;
import com.roohia.hp.laundry.gui.adapters.BasketItemsAdapter;
import com.roohia.hp.laundry.gui.adapters.NewOrderItemsAdapter;
import com.roohia.hp.laundry.gui.interfaces.CheckoutInterface;
import com.roohia.hp.laundry.model.bo.NewOrderItem;
import com.roohia.hp.laundry.model.database.DBHandler;
import com.roohia.hp.laundry.model.dbo.OrderItems;
import com.roohia.hp.laundry.model.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment implements  AdapterView.OnItemClickListener, View.OnClickListener, CheckoutInterface {

    private ListView lvOrderItems = null;
    private List<OrderItems> BasketItems = new ArrayList<>();
    private BasketItemsAdapter basketItemsAdapter = null;
    private Button checkOut;
    private Context mContext;


    public BasketFragment() {
    }

    public static BasketFragment newInstance(Context pContext) {
        Bundle args = new Bundle();
        BasketFragment fragment = new BasketFragment();
        fragment.mContext = pContext;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_basket, container, false);
        RelativeLayout root = (RelativeLayout) view.findViewById(R.id.root);
        checkOut = (Button) view.findViewById(R.id.btn_checkout);
        root.setOnClickListener(this);
        checkOut.setOnClickListener(this);
        lvOrderItems = (ListView) view.findViewById(R.id.order_items);
        basketItemsAdapter = new BasketItemsAdapter(getContext(), BasketItems,this);
        lvOrderItems.setAdapter(basketItemsAdapter);
        lvOrderItems.setOnItemClickListener(this);
        getAllOrderItems();
        return view;
    }


    private void getAllOrderItems() {
        ((HomeActivity) getActivity()).showProgressDialog("Loading...");
        BasketItems = DBHandler.getInstance().getOrderItemsFromBasket();
        basketItemsAdapter.setItems(BasketItems);
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
                    OrderItems orderItem = basketItemsAdapter.getItems().get((int)view.getTag());
                    orderItem.delete();
                    basketItemsAdapter.getItems().remove((int)view.getTag());
                    basketItemsAdapter.notifyDataSetChanged();
                }
                break;

            case R.id.btn_checkout:
                List<OrderItems>  basketItems = basketItemsAdapter.getItems();
                if(basketItems.size() > 0){
                    ((HomeActivity) getActivity()).showProgressDialog(getString(R.string.checking_out_prompt));
                    CheckoutController.getInstance().checkout(mContext,basketItems,DBHandler.getInstance().getCurrentOrderId(),DBHandler.getInstance().getCurrentUser().getUserId(),this);

                }else{
                    AlertUtils.showAlertDialog(mContext,"Add some items to basket for checkout!",null);
                }
                break;
        }
    }

    @Override
    public void onCheckoutSuccess() {
        ((HomeActivity) getActivity()).hideProgressDialog();
        AlertUtils.showAlertDialog(getContext(), getString(R.string.order_success_prompt),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((HomeActivity)getActivity()).clearBackStack();
            }
        });
    }

    @Override
    public void onCheckoutFailed(String errorCode, String message) {
        ((HomeActivity) getActivity()).hideProgressDialog();
        AlertUtils.showAlertDialog(getContext(), "Check out failed! Please try again.", null);
    }
}
