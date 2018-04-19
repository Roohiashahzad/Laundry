package com.roohia.hp.laundry.model.bo;

public class MyOrderItem {
    String orderNumber;
    String orderDate;
    String status;
    String OrderDetailsString;

    public MyOrderItem() {
    }

    public MyOrderItem(String orderNumber, String orderDate, String status, String orderDetailsString) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.status = status;
        this.OrderDetailsString = orderDetailsString;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDetailsString() {
        return OrderDetailsString;
    }

    public void setOrderDetailsString(String orderDetailsString) {
        OrderDetailsString = orderDetailsString;
    }
}
