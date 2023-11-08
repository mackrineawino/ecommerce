package com.ecom.app.model.entity;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

public class ItemCart {
    private String productName;
    private ProductCategory category;
    private double price;

    

    public ItemCart() {
    }
    public ItemCart( String name, ProductCategory category, double price) {
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
    public String tableRow(){
        
        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<tr>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(getProductName())).append("</td>");
        trBuilder.append("<td>").append(getCategory()).append("</td>");
        trBuilder.append("<td>").append(new DecimalFormat("#,###.##").format(getPrice())).append("</td>");
        trBuilder.append("<td>").append("<a href=\"#\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">").append("REMOVE").append("</a>");
        trBuilder.append("<tr>");

        return trBuilder.toString();
    }

}
