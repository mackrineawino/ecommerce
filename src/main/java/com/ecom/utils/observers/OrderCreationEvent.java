package com.ecom.utils.observers;

import com.ecom.app.model.entity.Order;

import java.io.Serializable;

public class OrderCreationEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Order newOrder;
    private final String orderNumber;

    public OrderCreationEvent(Order newOrder, String orderNumber) {
        this.newOrder = newOrder;
        this.orderNumber = orderNumber;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
