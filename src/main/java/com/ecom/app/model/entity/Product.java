package com.ecom.app.model.entity;

import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;

import com.ecom.app.model.view.html.DbTableAnnotation;
import com.ecom.app.model.view.html.DbTableColAnnotation;
import com.ecom.app.model.view.html.HtmlCardAnnotations;
import com.ecom.app.model.view.html.HtmlFormAnnotations;
import com.ecom.app.model.view.html.HtmlFormFieldAnnotation;
import com.ecom.app.model.view.html.HtmlTableColHeader;

@DbTableAnnotation(name="products")
@HtmlFormAnnotations(label="Add Items", url="./addProduct")
public class Product extends BaseEntity{
    // @DbTableColAnnotation(name="productId", definition = "int")
    // @HtmlFormFieldAnnotation(label="Product ID")
    // private Long productId;

    @DbTableColAnnotation(name="imageURL")
    @HtmlCardAnnotations(label="Image URL: ")
    @HtmlFormFieldAnnotation(label="Image URL")
    private String imageUrl;

    @DbTableColAnnotation(name="productName")
    @HtmlCardAnnotations(label="Product Name: ")
    @HtmlFormFieldAnnotation(label="Product Name")
     @HtmlTableColHeader(headerLabel = "Product Name")
    private String productName;

    @DbTableColAnnotation(name="productPrice")
     @HtmlCardAnnotations(label="Product Price: ")
    @HtmlFormFieldAnnotation(label="Price")
    @HtmlTableColHeader(headerLabel = "Product Price")
    private double price;

    @DbTableColAnnotation(name="productAvailability")
     @HtmlCardAnnotations(label="Product Availability: ")
    @HtmlFormFieldAnnotation(label="Product Availability")
    @HtmlTableColHeader(headerLabel = "Product Availability")
    private int availability;

   
    @DbTableColAnnotation(name="productDescription")
    @HtmlFormFieldAnnotation(label="Product Description")
    private String productDescription;
    
    @DbTableColAnnotation(name="productCategory")
    @HtmlFormFieldAnnotation(label="Product Category")
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
