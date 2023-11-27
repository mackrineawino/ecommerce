package com.ecom.app.model.entity;

import java.util.List;

import com.ecom.app.model.view.html.DbTableAnnotation;
import com.ecom.app.model.view.html.DbTableColAnnotation;

@DbTableAnnotation(name="orders")
public class Order extends BaseEntity{
    @DbTableColAnnotation(name="userEmail")
    private String email;
    @DbTableColAnnotation(name="totalItemsAmount")
    private double totalAmount;
    @DbTableColAnnotation(name="orderstatus")
    private OrderStatus status;
    @DbTableColAnnotation(name="orderItems")
    private List<ItemCart> orderItems;
    public Order(Long id, String email, double totalAmount, OrderStatus status, List<ItemCart> orderItems) {
      setId(id);
        this.email = email;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderItems = orderItems;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public List<ItemCart> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<ItemCart> orderItems) {
        this.orderItems = orderItems;
    }

    
}
