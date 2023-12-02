package com.ecom.utils;

import com.ecom.app.model.entity.Order;

import java.io.Serializable;

public class OrderCreationEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Order newOrder;

    public OrderCreationEvent(Order newOrder) {
        this.newOrder = newOrder;
    }

    public Order getNewOrder() {
        return newOrder;
    }
}
