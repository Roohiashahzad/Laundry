package com.roohia.hp.laundry.gui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.roohia.hp.laundry.R;
import com.roohia.hp.laundry.model.bo.NavItem;

import java.util.ArrayList;


public class NavigationListAdapter extends ArrayAdapter {
    private ArrayList<NavItem> items;
    private Context context;

    public NavigationListAdapter(Context context, ArrayList<NavItem> items) {
        super(context, R.layout.layout_nav_menu_item);
        this.items = items;
        this.context = context;
    }

    public ArrayList<NavItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<NavItem> items) {
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_nav_menu_item, null);
        }

        NavItem menuItem = items.get(position);
        TextView tvTitle = (TextView) view.findViewById(R.id.nav_item_title);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.nav_item_icon);
        RelativeLayout root = (RelativeLayout) view.findViewById(R.id.rlt_nav_item_root);
        if(!menuItem.isSelected()) {

            root.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
            tvTitle.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            tvTitle.setText(menuItem.getTitle());

        }
        else{
            root.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white));
            tvTitle.setText(menuItem.getTitle());

        }
        ivIcon.setImageResource(menuItem.getIcon());

        return view;
    }
}
