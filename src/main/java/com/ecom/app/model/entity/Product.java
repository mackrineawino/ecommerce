package com.ecom.app.model.entity;

import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;

public class Product {
    private Long productId;
    private String productName;
    private String productDescription;
    private double price;
    private int availability;
    private ProductCategory category;
    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String name, String description, double price, int availability, ProductCategory category,
            String imageUrl) {
        this.productId = id;
        this.productName = name;
        this.productDescription = description;
        this.price = price;
        this.availability = availability;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
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
    
        trBuilder.append("<div style=\" border-radius: 5px; margin-top: 20px\">");
        trBuilder.append("<img style=\"height: 250px; width: 250px;\" src=\"").append(getImageUrl()).append("\" alt=\"").append(getProductName()).append("\"/>");
        trBuilder.append("<h3>").append("Name: ").append(StringUtils.trimToEmpty(getProductName())).append("</h3>");
        trBuilder.append("<h4>").append("Ksh: ").append(new DecimalFormat("#,###.##").format(getPrice())).append("</h4>");
        trBuilder.append("<h4>").append("Availability: ").append(getAvailability()).append(" In Stock").append("</h4>");
        trBuilder.append("<a href=\"#\" style=\" text-decoration: none;  padding: 10px 25px; color: white; background: #49A3C8; border-radius: 3px; \">").append("BUY").append("</a>");

        trBuilder.append("</div>");
    
        return trBuilder.toString();
    }
    
}
