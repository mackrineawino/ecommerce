package com.ecom.app.model.view.html;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

import com.ecom.app.model.entity.Product;

public class HtmlProductDesc {
    static Product product=new Product();
        public static String displayProductDescription(Product product) {
        StringBuilder divStringBuilder = new StringBuilder();
        divStringBuilder.append("<div style=\"color: white; display: flex; margin-top: 50px; background: #292929;\">");
        divStringBuilder.append(
                "<div style=\" height: 450px;margin-left: 50px; background: #0A0C09; box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5); \">");
        divStringBuilder.append("<img style=\"height: 400px; width: 400px;\" src=\"").append(product.getImageUrl())
                .append("\" alt=\"").append(product.getProductName()).append("\" />");
        divStringBuilder.append("</div>");
        divStringBuilder.append("<div style=\"margin-left: 70px;\">");
        divStringBuilder.append("<h3 style=\" font-size: 30px;\">").append("Name: ")
                .append(StringUtils.trimToEmpty(product.getProductName())).append("</h3>");
        divStringBuilder.append("<h4 style=\" font-size: 30px;\">").append("Ksh: ").append(new DecimalFormat("#,###.##").format(product.getPrice()))
                .append("</h4>");
        divStringBuilder.append("<h3 style=\" font-size: 30px;\">").append("Category: ").append(product.getCategory()).append("</ style=\" font-size: 30px;\">");
        divStringBuilder.append("<h3 style=\" font-size: 30px;\">").append("Description: ").append(StringUtils.trimToEmpty(product.getProductDescription()))
                .append("</h3>");
        divStringBuilder.append("<button id=\"" + product.getId() + "\" productName=\""
                + product.getProductName() + "\" category=\"" + product.getCategory() + "\" price=\"" + product.getPrice()
                + "\" onclick=\"addToCartClick(event)\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">")
                .append("ADD TO CART").append("</button>");
        divStringBuilder.append("</div>");
        divStringBuilder.append("</div>");

        return divStringBuilder.toString();
    }

}
