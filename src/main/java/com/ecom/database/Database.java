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

}
