package com.ecom.app.model.entity;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class ItemCart {
    private Long productId;
    private String productName;
    private ProductCategory category;
    private double price;

    

    public ItemCart() {
    }
    public ItemCart(Long id, String name, ProductCategory category, double price) {
        this.productId=id;
        this.productName=name;
        this.category = category;
        this.price=price;
  
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
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String tableRow(){
        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<tr>");
        trBuilder.append("<td>").append(getProductId()).append("</td>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(getProductName())).append("</td>");
        trBuilder.append("<td>").append(getCategory()).append("</td>");
        trBuilder.append("<td>").append(new DecimalFormat("#,###.##").format(getPrice())).append("</td>");
        trBuilder.append("<td>").append("<button onclick=\"removeItem(event)\" productId=\"" + getProductId() + "\"  style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">REMOVE</button>").append("</td>");
        trBuilder.append("<tr>");
    
        return trBuilder.toString();
    }
    
    

}
