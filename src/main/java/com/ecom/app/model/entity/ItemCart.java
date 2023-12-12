package com.ecom.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecom.app.model.view.html.HtmlTableColHeader;

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

    @ManyToOne
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ItemCart() {
    }

    public ItemCart(Long id, String imageUrl, String name, ProductCategory category, double price, Order order) {
        setId(id);
        this.imageUrl = imageUrl;
        this.productName = name;
        this.category = category;
        this.price = price;
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

}