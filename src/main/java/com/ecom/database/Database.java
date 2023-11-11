package com.ecom.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.User;

public class Database implements Serializable {

    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<ItemCart> cartItems = new ArrayList<>();

    private static Database dbInstance;

    private Database() {
    }

    public static Database getDbInstance() {
        if (dbInstance == null) {
            dbInstance = new Database();

        }

        return dbInstance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<ItemCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ItemCart> cartItems) {
        this.cartItems = cartItems;
    }
    public boolean updateProduct(Product updatedProduct) {
        // Find the product to update by comparing product IDs
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductId().equals(updatedProduct.getProductId())) {
                // Update the product details
                product.setProductName(updatedProduct.getProductName());
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                product.setAvailability(updatedProduct.getAvailability());
                product.setImageUrl(updatedProduct.getImageUrl());
                product.setProductDescription(updatedProduct.getProductDescription());
                // Optionally, you can save the updated product to the list
                // products.set(i, product);

                // Return true to indicate a successful update
                return true;
            }
        }

        // If the product was not found, return false to indicate failure
        return false;
    }
}
