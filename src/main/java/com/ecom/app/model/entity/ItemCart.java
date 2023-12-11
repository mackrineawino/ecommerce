package com.ecom.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecom.app.model.view.html.HtmlTableColHeader;

@Entity
@Table(name = "itemcart")
public class ItemCart extends BaseEntity{

    @Column(name="productName")
    @HtmlTableColHeader(headerLabel = "Product Name")
    private String productName;

    @Column(name="productCategory")
    @HtmlTableColHeader(headerLabel = "Product Category")
    private ProductCategory category;

    @Column(name="productPrice")
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

    public ItemCart(Long id, String name, ProductCategory category, double price, Order order) {
        setId(id);
        this.productName = name;
        this.category = category;
        this.price = price;
        this.order=order;

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

    // public Long getProductId() {
    //     return productId;
    // }

    // public void setProductId(Long productId) {
    //     this.productId = productId;
    // }
    // public String tableRow(){
    // StringBuilder trBuilder = new StringBuilder();
    // trBuilder.append("<tr>");
    // trBuilder.append("<td>").append(getProductId()).append("</td>");
    // trBuilder.append("<td>").append(StringUtils.trimToEmpty(getProductName())).append("</td>");
    // trBuilder.append("<td>").append(getCategory()).append("</td>");
    // trBuilder.append("<td>").append(new
    // DecimalFormat("#,###.##").format(getPrice())).append("</td>");
    // trBuilder.append("<td>").append("<button onclick=\"removeItem(event)\"
    // productId=\"" + getProductId() + "\" style=\"text-decoration: none; padding:
    // 10px 25px; color: white; background: #E0588E; border-radius:
    // 3px;\">REMOVE</button>").append("</td>");
    // trBuilder.append("<tr>");

    // return trBuilder.toString();
    // }

}
