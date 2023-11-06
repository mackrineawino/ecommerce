package com.ecom.app.bean;

import com.ecom.app.model.entity.Product;

public interface ProductBean {
    String productList();

    Product addOrUpdateProduct(Product product) throws Exception;

    void deleteAccount(Product product);

}
