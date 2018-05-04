package com.roohia.hp.laundry.model.dbo;

import com.orm.SugarRecord;


public class OrderRecord extends SugarRecord {

    String orderId;
    String orderPickupDate;
    String orderDropOffDate;
    int activeStatus;

    public OrderRecord() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getActive() {
        return activeStatus;
    }

    public void setActive(int active) {
        this.activeStatus = active;
    }

    public String getOrderPickupDate() {
        return orderPickupDate;
    }

    public void setOrderPickupDate(String orderPickupDate) {
        this.orderPickupDate = orderPickupDate;
    }

    public String getOrderDropOffDate() {
        return orderDropOffDate;
    }

    public void setOrderDropOffDate(String orderDropOffDate) {
        this.orderDropOffDate = orderDropOffDate;
    }
}
