package com.roohia.hp.laundry.gui.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.gui.Fragments.BasketFragment;
import com.roohia.hp.laundry.gui.activities.HomeActivity;
import com.roohia.hp.laundry.model.bo.NewOrderItem;
import com.roohia.hp.laundry.model.dbo.OrderItems;
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.model.utils.CodeUtils;

import java.util.ArrayList;
import java.util.List;


public class BasketItemsAdapter extends ArrayAdapter {

    private List<OrderItems> items;
    private Context context;
    private BasketFragment fragment;

    public BasketItemsAdapter(Context context, List<OrderItems> items, BasketFragment fragment) {
        super(context, R.layout.layout_basket_item_row);
        this.items = items;
        this.context = context;
        this.fragment = fragment;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_basket_item_row, null);
        }

        OrderItems newOrderItem = items.get(position);
        final RelativeLayout rltItemDetails = (RelativeLayout) view.findViewById(R.id.rlt_item_details);

        final TextView tvClothName = (TextView) view.findViewById(R.id.cloth_name);
        Button btnRemoveFromCart = (Button) view.findViewById(R.id.btn_remove);

        LinearLayout llWashQuantity = (LinearLayout) view.findViewById(R.id.ll_wash_quantity);
        LinearLayout llPressQuantity = (LinearLayout) view.findViewById(R.id.ll_Press_quantity);
        final EditText etWashQuantity = (EditText) view.findViewById(R.id.et_wash_quantity);
        final EditText etPressQuantity = (EditText) view.findViewById(R.id.et_press_quantity);
        ImageView ivOpen = (ImageView) view.findViewById(R.id.iv_open);
        tvClothName.setText(newOrderItem.getItemName());

        ivOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rltItemDetails.getVisibility() == View.VISIBLE)
                    rltItemDetails.setVisibility(View.GONE);
                else
                    rltItemDetails.setVisibility(View.VISIBLE);
            }
        });

        if(Integer.parseInt(newOrderItem.getItemWashCount()) > 0) {
            llWashQuantity.setVisibility(View.VISIBLE);
            etWashQuantity.setText(newOrderItem.getItemWashCount());
        }
        else {
            llWashQuantity.setVisibility(View.GONE);
        }

        if(Integer.parseInt(newOrderItem.getItemPressCount()) > 0) {
            llPressQuantity.setVisibility(View.VISIBLE);
            etPressQuantity.setText(newOrderItem.getItemPressCount() + "");
        }
        else {
            llPressQuantity.setVisibility(View.GONE);
        }

        btnRemoveFromCart.setTag(position);
        btnRemoveFromCart.setOnClickListener(fragment);

        tvClothName.setSelected(true);
        tvClothName.requestFocus();

        return view;
    }


}
