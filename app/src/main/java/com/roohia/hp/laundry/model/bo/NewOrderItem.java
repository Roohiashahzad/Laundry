package com.roohia.hp.laundry.model.bo;


public class NewOrderItem {

    String itemName;
    int iconId;
    int pressQuantity;
    int washQuantity;

    public NewOrderItem() {
    }

    public NewOrderItem(String itemName, int iconId) {
        this.itemName = itemName;
        this.iconId = iconId;
    }

    public NewOrderItem(String itemName, int iconId, int pressQuantity, int washQuantity) {
        this.itemName = itemName;
        this.iconId = iconId;
        this.pressQuantity = pressQuantity;
        this.washQuantity = washQuantity;
    }

    public int getPressQuantity() {
        return pressQuantity;
    }

    public void setPressQuantity(int pressQuantity) {
        this.pressQuantity = pressQuantity;
    }

    public int getWashQuantity() {
        return washQuantity;
    }

    public void setWashQuantity(int washQuantity) {
        this.washQuantity = washQuantity;
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
