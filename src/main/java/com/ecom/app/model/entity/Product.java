package com.ecom.app.model.entity;

import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;

import com.ecom.app.model.view.html.HtmlCardAnnotations;
import com.ecom.app.model.view.html.HtmlFormAnnotations;
import com.ecom.app.model.view.html.HtmlFormFieldAnnotation;
@HtmlFormAnnotations(label="Add Items", url="./addProduct")
public class Product {

    @HtmlFormFieldAnnotation(label="Product ID")
    private Long productId;

     @HtmlCardAnnotations(label="Image URL: ")
    @HtmlFormFieldAnnotation(label="Image URL")
    private String imageUrl;

    @HtmlCardAnnotations(label="Product Name: ")
    @HtmlFormFieldAnnotation(label="Product Name")
    private String productName;

     @HtmlCardAnnotations(label="Product Price: ")
    @HtmlFormFieldAnnotation(label="Price")
    private double price;

     @HtmlCardAnnotations(label="Product Availability: ")
    @HtmlFormFieldAnnotation(label="Product Availability")
    private int availability;

   

    @HtmlFormFieldAnnotation(label="Product Description")
    private String productDescription;
    
    @HtmlFormFieldAnnotation(label="Product Category")
    private ProductCategory category;

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

    public String displayProductDescription() {
        StringBuilder divStringBuilder = new StringBuilder();
        divStringBuilder.append("<div style=\"color: white; display: flex; margin-top: 50px; background: #292929;\">");
        divStringBuilder.append(
                "<div style=\" height: 450px;margin-left: 50px; background: #0A0C09; box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5); \">");
        divStringBuilder.append("<img style=\"height: 400px; width: 400px;\" src=\"").append(getImageUrl())
                .append("\" alt=\"").append(getProductName()).append("\" />"); // Add double quotes and close the img
                                                                               // tag
        divStringBuilder.append("</div>");
        divStringBuilder.append("<div style=\"margin-left: 70px;\">");
        divStringBuilder.append("<h3 style=\" font-size: 30px;\">").append("Name: ")
                .append(StringUtils.trimToEmpty(getProductName())).append("</h3>");
        divStringBuilder.append("<h4 style=\" font-size: 30px;\">").append("Ksh: ").append(new DecimalFormat("#,###.##").format(getPrice()))
                .append("</h4>");
        divStringBuilder.append("<h3 style=\" font-size: 30px;\">").append("Category: ").append(getCategory()).append("</ style=\" font-size: 30px;\">");
        divStringBuilder.append("<h3 style=\" font-size: 30px;\">").append("Description: ").append(StringUtils.trimToEmpty(getProductDescription()))
                .append("</h3>");
        divStringBuilder.append("<button id=\"addToCartButton\" productId=\"" + getProductId() + "\" productName=\""
                + getProductName() + "\" category=\"" + getCategory() + "\" price=\"" + getPrice()
                + "\" onclick=\"addToCartClick(event)\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">")
                .append("ADD TO CART").append("</button>");
        divStringBuilder.append("</div>");
        divStringBuilder.append("</div>");

        return divStringBuilder.toString();
    }

}
