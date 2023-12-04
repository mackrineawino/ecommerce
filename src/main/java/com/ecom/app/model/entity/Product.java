package com.ecom.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.ecom.app.model.view.html.HtmlCardAnnotations;
import com.ecom.app.model.view.html.HtmlFormAnnotations;
import com.ecom.app.model.view.html.HtmlFormFieldAnnotation;
import com.ecom.app.model.view.html.HtmlTableColHeader;

@Entity
@Table(name = "products")
@HtmlFormAnnotations(label = "Add Items", url = "./addProduct")
public class Product extends BaseEntity {
    // @DbTableColAnnotation(name="productId", definition = "int")
    // @HtmlFormFieldAnnotation(label="Product ID")
    // private Long productId;

    @Column(name = "imageURL")
    @HtmlCardAnnotations(label = "Image URL: ")
    @HtmlFormFieldAnnotation(label = "Image URL")
    private String imageUrl;

    @Column(name = "productName")
    @HtmlCardAnnotations(label = "Product Name: ")
    @HtmlFormFieldAnnotation(label = "Product Name")
    @HtmlTableColHeader(headerLabel = "Product Name")
    private String productName;

    @Column(name = "productPrice")
    @HtmlCardAnnotations(label = "Product Price: ")
    @HtmlFormFieldAnnotation(label = "Price")
    @HtmlTableColHeader(headerLabel = "Product Price")
    private double price;

    @Column(name = "productAvailability")
    @HtmlCardAnnotations(label = "Product Availability: ")
    @HtmlFormFieldAnnotation(label = "Product Availability")
    @HtmlTableColHeader(headerLabel = "Product Availability")
    private int availability;

    @Column(name = "productDescription", columnDefinition = "longtext")
    @HtmlFormFieldAnnotation(label = "Product Description")
    private String productDescription;

    @Column(name = "productCategory")
     @Enumerated(EnumType.STRING)
    @HtmlFormFieldAnnotation(label = "Product Category")
    @HtmlTableColHeader(headerLabel = "Product Category")
    private ProductCategory category;

    public Product() {
    }

    public Product(Long id, String name, String description, double price, int availability, ProductCategory category,
            String imageUrl) {
        setId(id);
        this.productName = name;
        this.productDescription = description;
        this.price = price;
        this.availability = availability;
        this.category = category;
        this.imageUrl = imageUrl;
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

}
