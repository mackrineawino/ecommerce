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
    
        trBuilder.append("<div class=\"display_product\" style=\"border-radius: 5px; margin-top: 20px; text-align: center; background: #C2D7EB; box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5); transition: all 0.3s; display: inline-block; transform-origin: center;\">");
        trBuilder.append("<img style=\"height: 250px; width: 250px;\" src=\"").append(getImageUrl()).append("\" alt=\"").append(getProductName()).append("\"/>");
        trBuilder.append("<h3>").append("Name: ").append(StringUtils.trimToEmpty(getProductName())).append("</h3>");
        trBuilder.append("<h4>").append("Ksh: ").append(new DecimalFormat("#,###.##").format(getPrice())).append("</h4>");
        trBuilder.append("<h4>").append("Availability: ").append(getAvailability()).append(" In Stock").append("</h4>");
        trBuilder.append("<a href=\"#\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">").append("BUY").append("</a>");
        trBuilder.append("</div>");
        
        // Add hover effect inline styles
        trBuilder.append("<style>");
        trBuilder.append(".display_product:hover {");
        trBuilder.append("  box-shadow: 8px 8px 12px rgba(0, 0, 0, 0.7);"); // Adjust the shadow on hover
        trBuilder.append("  transform: scale(1.1);"); // Scale up the div on hover
        trBuilder.append("}");
        trBuilder.append("</style>");
        
    
        return trBuilder.toString();
    }
    
}
