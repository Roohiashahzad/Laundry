package com.roohia.hp.laundry.model.dbo;

import com.orm.SugarRecord;


public class OrderItems extends SugarRecord {

    String orderId;
    String itemName;
    String itemPressCount;
    String itemWashCount;
    String orderDetailsId;

    public OrderItems() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPressCount() {
        return itemPressCount;
    }

    public void setItemPressCount(String itemPressCount) {
        this.itemPressCount = itemPressCount;
    }

    public String getItemWashCount() {
        return itemWashCount;
    }

    public void setItemWashCount(String itemWashCount) {
        this.itemWashCount = itemWashCount;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }
}
