package com.ecom.app.model.entity;

import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;

public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private double price;
    private int availability;
    private String category;
    private String imageUrl;

    public Product() {
    }

    public Product(String id, String name, String description, double price, int availability, String category,
            String imageUrl) {
        this.productId = id;
        this.productName = name;
        this.productDescription = description;
        this.price = price;
        this.availability = availability;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String displayProducts() {
        StringBuilder trBuilder = new StringBuilder();
    
        trBuilder.append("<div class=\\\"product-item\\\">");
        trBuilder.append("<img src=\"").append(getImageUrl()).append("\" alt=\"").append(getProductName()).append("\"/>");
        trBuilder.append("<h3>").append(StringUtils.trimToEmpty(getProductName())).append("</h3>");
        trBuilder.append("<h4>").append(new DecimalFormat("#,###.##").format(getPrice())).append("</h4>");
        trBuilder.append("<h4>").append(getAvailability()).append("</h4>");
        trBuilder.append("</div>");
    
        return trBuilder.toString();
    }
    
}
