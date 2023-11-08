package com.ecom.app.bean;

import java.util.ArrayList; // Import ArrayList
import java.util.List;

import com.ecom.app.model.entity.ItemCart;
import com.ecom.database.Database;

public class CartBeanImpl implements CartBeanI {
    // Initialize the cartItems list in the constructor
    private List<ItemCart> cartItems;

    public CartBeanImpl() {
        cartItems = new ArrayList<>(); // Initialize cartItems as an ArrayList
        cartItems.addAll(Database.getDbInstance().getCartItems());
    }

    @Override
    public String CartProducts() {
        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<div style=\" text-align: center; color: white; background: #292929;\"><table style=\"border-collapse: separate; border-spacing: 20px; margin: 0 auto; width: 80%;\"><tr><th>Product Name</th><th>Category</th><th>Price</th><th>Delete</th></tr>");

        for (ItemCart cartItem : cartItems)
            trBuilder.append(cartItem.tableRow());

        trBuilder.append("</table></div>");

        return trBuilder.toString();
    }

    @Override
    public ItemCart addItemToCart(ItemCart item) {
        cartItems.add(item);
        return item;
    }

     public void deleteFromCart(ItemCart item) {

     }
}
