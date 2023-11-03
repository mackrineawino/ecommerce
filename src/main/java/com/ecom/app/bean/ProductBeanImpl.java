package com.ecom.app.bean;

import java.util.ArrayList;
import java.util.List;

import com.ecom.app.model.entity.Product;
import com.ecom.database.Database;

public class ProductBeanImpl implements ProductBean {

    @Override
    public String productList() {
    
        List<Product> products = Database.getDbInstance().getProducts();

        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<div >");

        for (Product product : products)
            trBuilder.append(product.displayProducts());

        trBuilder.append("</div>");

        return trBuilder.toString();
    }

    public Product addOrUpdateAccount(Product product) throws Exception {

        return null;
    }

    public void deleteAccount(Product product) {

    }

}
