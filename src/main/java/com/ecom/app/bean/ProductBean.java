package com.ecom.app.bean;

import com.ecom.app.model.Product;

public interface ProductBean {
    String productList();

    Product addOrUpdateAccount(Product product) throws Exception;

    void deleteAccount(Product product);

}
