package com.roohia.hp.laundry.model.bo;


public class NewOrderItem {

    String itemName;
    int iconId;

    public NewOrderItem() {
    }

    public NewOrderItem(String itemName, int iconId) {
        this.itemName = itemName;
        this.iconId = iconId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
