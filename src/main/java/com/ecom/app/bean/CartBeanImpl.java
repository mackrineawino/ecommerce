package com.ecom.app.bean;

import java.util.Iterator;
import java.util.List;

import com.ecom.app.model.entity.ItemCart;

public class CartBeanImpl extends GenericBeanImpl<ItemCart> implements CartBeanI {

    double totalPrice = 0.0;

    public void deleteProduct(Long productId) {
        System.out.println("Trying to delete product with ID: " + productId);
        List<ItemCart> cartItems = list(ItemCart.class);
    
        Iterator<ItemCart> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            ItemCart cartItem = iterator.next();
            if (cartItem.getId().equals(productId)) {
                System.out.println("Item found. Removing from the cart.");
                totalPrice -= cartItem.getPrice();
                iterator.remove();
                return;
            }
        }
        System.out.println("Item not found in the cart for productId: " + productId);
    }
}
