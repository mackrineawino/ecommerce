package com.ecom.app.bean;

import com.ecom.app.model.entity.ItemCart;


public interface CartBeanI {
    public String CartProducts();
    public ItemCart addItemToCart(ItemCart cart);
  public void deleteFromCart(Long productId);

}
