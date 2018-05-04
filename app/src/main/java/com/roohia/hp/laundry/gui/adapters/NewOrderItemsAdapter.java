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
import com.roohia.hp.laundry.gui.activities.HomeActivity;
import com.roohia.hp.laundry.model.bo.NewOrderItem;
import com.roohia.hp.laundry.model.database.DBHandler;
import com.roohia.hp.laundry.model.utils.AlertUtils;
import com.roohia.hp.laundry.model.utils.CodeUtils;
import com.roohia.hp.laundry.model.utils.PreferenceUtils;

import java.util.ArrayList;


public class NewOrderItemsAdapter extends ArrayAdapter {

    private ArrayList<NewOrderItem> items;
    private Context context;

    public NewOrderItemsAdapter(Context context, ArrayList<NewOrderItem> items) {
        super(context, R.layout.layout_new_order_item_row);
        this.items = items;
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_new_order_item_row, null);
        }

        NewOrderItem newOrderItem = items.get(position);
        final RelativeLayout rltItemDetails = (RelativeLayout) view.findViewById(R.id.rlt_item_details);
        ImageView ivClothIcon = (ImageView) view.findViewById(R.id.iv_cloth_icon);
        final TextView tvClothName = (TextView) view.findViewById(R.id.cloth_name);
        Button btnAddtoCart = (Button) view.findViewById(R.id.btn_add_to_cart);
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
                    CodeUtils.getInstance().hideKeyboard((HomeActivity)context);
                    String orderId = DBHandler.getInstance().getCurrentOrderId();
                    int itemDetailsId = PreferenceUtils.getItemId();
                    PreferenceUtils.saveItemId(context,itemDetailsId);
                    DBHandler.getInstance().saveNewOrderItem(orderId, itemDetailsId+"", clothName,pressCount+"",washCount+"");

                    AlertUtils.showAlertDialog(context,  clothName + " added to basket for press: " + String.valueOf(pressCount) + "\n" + clothName + " added to basket for wash: " + String.valueOf(washCount), new DialogInterface.OnClickListener() {
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
