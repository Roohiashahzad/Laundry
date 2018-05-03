package com.roohia.hp.laundry.model.dbo;

import com.orm.SugarRecord;


public class Order extends SugarRecord {

    String orderId;
    String orderPickupDate;
    String orderDropOffDate;
    int active;

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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
