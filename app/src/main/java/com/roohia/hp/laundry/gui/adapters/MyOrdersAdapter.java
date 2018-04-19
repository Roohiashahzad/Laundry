package com.roohia.hp.laundry.gui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.model.bo.MyOrderItem;

import java.util.ArrayList;


public class MyOrdersAdapter extends ArrayAdapter {

    private ArrayList<MyOrderItem> items;
    private Context context;

    public MyOrdersAdapter(Context context, ArrayList<MyOrderItem> items) {
        super(context, R.layout.layout_my_order_item_row);
        this.items = items;
        this.context = context;
    }

    public ArrayList<MyOrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MyOrderItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_my_order_item_row, null);
        }

        MyOrderItem myOrderItem = items.get(position);
        LinearLayout llOrderItemHeader = (LinearLayout) view.findViewById(R.id.ll_my_order_item_header);
        final LinearLayout llOrderItemDetails = (LinearLayout) view.findViewById(R.id.ll_item_details);
        TextView tvOrderNumber = (TextView) view.findViewById(R.id.col_order_number);
        TextView tvOrderDate = (TextView) view.findViewById(R.id.col_order_date);
        TextView tvStatus = (TextView) view.findViewById(R.id.col_status);
        TextView tvDetails = (TextView) view.findViewById(R.id.tv_my_order_details);

        tvOrderNumber.setText(myOrderItem.getOrderNumber());
        tvOrderDate.setText(myOrderItem.getOrderDate());
        tvStatus.setText(myOrderItem.getStatus());
        tvDetails.setText(myOrderItem.getOrderDetailsString());

        llOrderItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(llOrderItemDetails.getVisibility() == View.GONE)
                    llOrderItemDetails.setVisibility(View.VISIBLE);
                else
                    llOrderItemDetails.setVisibility(View.GONE);
            }
        });



        return view;
    }


}
