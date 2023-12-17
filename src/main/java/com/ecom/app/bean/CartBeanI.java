package com.ecom.app.bean;


import java.util.List;

import com.ecom.app.model.entity.ItemCart;


public interface CartBeanI extends GenericBeanI<ItemCart> {
    // List<ItemCart> Itemslist(Class<ItemCart> clazz);
    void addMoreQuantity(Long itemId);
    void addOrUpdateCartItems(List<ItemCart> cartItems);
    void clearCart();
    void addOrUpdate(ItemCart itemCart);
    void reduceQuantity(Long id) ;

}
