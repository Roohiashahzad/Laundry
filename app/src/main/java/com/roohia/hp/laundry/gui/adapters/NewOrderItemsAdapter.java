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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.model.bo.OrderItem;
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.model.utils.CodeUtils;

import java.util.ArrayList;


public class NewOrderItemsAdapter extends ArrayAdapter {

    private ArrayList<OrderItem> items;
    private Context context;

    public NewOrderItemsAdapter(Context context, ArrayList<OrderItem> items) {
        super(context, R.layout.layout_new_order_item_row);
        this.items = items;
        this.context = context;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_new_order_item_row, null);
        }

        OrderItem orderItem = items.get(position);
        final RelativeLayout rltItemDetails = (RelativeLayout) view.findViewById(R.id.rlt_item_details);
        ImageView ivClothIcon = (ImageView) view.findViewById(R.id.iv_cloth_icon);
        final TextView tvClothName = (TextView) view.findViewById(R.id.cloth_name);
        Button btnAddtoCart = (Button) view.findViewById(R.id.btn_add_to_cart);
        final EditText etWashQuantity = (EditText) view.findViewById(R.id.et_wash_quantity);
        final EditText etPressQuantity = (EditText) view.findViewById(R.id.et_press_quantity);
        ImageView ivOpen = (ImageView) view.findViewById(R.id.iv_open);
        ivClothIcon.setImageDrawable(context.getResources().getDrawable(orderItem.getIconId()));
        tvClothName.setText(orderItem.getItemName());

        ivOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rltItemDetails.getVisibility() == View.VISIBLE)
                    rltItemDetails.setVisibility(View.GONE);
                else
                    rltItemDetails.setVisibility(View.VISIBLE);
            }
        });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int washCount = CodeUtils.getInstance().convertStringToInt(etWashQuantity.getText().toString());
                int pressCount = CodeUtils.getInstance().convertStringToInt(etPressQuantity.getText().toString());
                String clothName = tvClothName.getText().toString();

                if(washCount == 0 && pressCount == 0 ){
                    AlertUtils.showAlertDialog(context,"Please add wash / press quantities before adding to cart!", null);
                }
                else{
                    AlertUtils.showAlertDialog(context,  clothName + " for press :" + String.valueOf(pressCount) + "\n" + clothName + " for wash :" + String.valueOf(pressCount), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            rltItemDetails.setVisibility(View.GONE);
                        }
                    });
                }

            }
        });

        tvClothName.setSelected(true);
        tvClothName.requestFocus();

        return view;
    }


}
