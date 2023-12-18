package com.ecom.app.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecom.app.model.view.html.HtmlTableColHeader;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @HtmlTableColHeader(headerLabel = "Order Number")
    @Column(name = "orderNumber", nullable = false, unique = true)
    private String orderNumber;

    @HtmlTableColHeader(headerLabel = "Customer Email")
    @Column(name = "userEmail")
    private String email;

    @HtmlTableColHeader(headerLabel = "Amount Payable")
    @Column(name = "totalItemsAmount")
    private double totalAmount;

    @HtmlTableColHeader(headerLabel = "Order Status")
    @Column(name = "orderstatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @HtmlTableColHeader(headerLabel = "Items")
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<ItemCart> orderItems;

    public Order() {
    }

    public Order(Long id, String orderNumber, String email, double totalAmount, OrderStatus status,
            List<ItemCart> orderItems) {
        setId(id);
        this.orderNumber = orderNumber;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

}
