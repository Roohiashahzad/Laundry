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
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.model.utils.CodeUtils;

import java.util.ArrayList;


public class BasketItemsAdapter extends ArrayAdapter {

    private ArrayList<NewOrderItem> items;
    private Context context;
    private BasketFragment fragment;

    public BasketItemsAdapter(Context context, ArrayList<NewOrderItem> items, BasketFragment fragment) {
        super(context, R.layout.layout_basket_item_row);
        this.items = items;
        this.context = context;
        this.fragment = fragment;
    }

    public ArrayList<NewOrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<NewOrderItem> items) {
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

        NewOrderItem newOrderItem = items.get(position);
        final RelativeLayout rltItemDetails = (RelativeLayout) view.findViewById(R.id.rlt_item_details);
        ImageView ivClothIcon = (ImageView) view.findViewById(R.id.iv_cloth_icon);
        final TextView tvClothName = (TextView) view.findViewById(R.id.cloth_name);
        Button btnRemoveFromCart = (Button) view.findViewById(R.id.btn_remove);

        LinearLayout llWashQuantity = (LinearLayout) view.findViewById(R.id.ll_wash_quantity);
        LinearLayout llPressQuantity = (LinearLayout) view.findViewById(R.id.ll_Press_quantity);
        final EditText etWashQuantity = (EditText) view.findViewById(R.id.et_wash_quantity);
        final EditText etPressQuantity = (EditText) view.findViewById(R.id.et_press_quantity);
        ImageView ivOpen = (ImageView) view.findViewById(R.id.iv_open);
        ivClothIcon.setImageDrawable(context.getResources().getDrawable(newOrderItem.getIconId()));
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

        if(newOrderItem.getWashQuantity() > 0) {
            llWashQuantity.setVisibility(View.VISIBLE);
            etWashQuantity.setText(newOrderItem.getWashQuantity() + "");
        }
        else {
            llWashQuantity.setVisibility(View.GONE);
        }

        if(newOrderItem.getPressQuantity() > 0) {
            llPressQuantity.setVisibility(View.VISIBLE);
            etPressQuantity.setText(newOrderItem.getWashQuantity() + "");
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
