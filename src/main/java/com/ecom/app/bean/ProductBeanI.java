package com.ecom.app.bean;

import com.ecom.app.model.entity.Product;

public interface ProductBeanI {
    String productList();

    Product addOrUpdateProduct(Product product);

    void deleteAccount(Product product);

}
