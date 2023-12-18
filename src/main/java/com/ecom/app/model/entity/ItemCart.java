package com.ecom.app.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecom.app.model.view.html.HtmlTableColHeader;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "itemcart")
public class ItemCart extends BaseEntity {
    @HtmlTableColHeader(headerLabel = "imageUrl")
    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "productName")
    @HtmlTableColHeader(headerLabel = "Product Name")
    private String productName;

    @Column(name = "productCategory")
    @HtmlTableColHeader(headerLabel = "Product Category")
    private ProductCategory category;

    @Column(name = "productPrice")
    @HtmlTableColHeader(headerLabel = "Product Price")
    private double price;

    @Column(name = "quanity")
    @HtmlTableColHeader(headerLabel = "quantity")
    private Integer quantity = 1;

   @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "productid")
    private Long productid;

    public ItemCart() {
    }

    public ItemCart(Long id, Long productid, String imageUrl, String name, ProductCategory category, double price,
            Order order, Integer quanity) {
        setId(id);
        this.productid = productid;
        this.imageUrl = imageUrl;
        this.productName = name;
        this.category = category;
        this.price = price;
        this.order = order;
        this.quantity = quanity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

}