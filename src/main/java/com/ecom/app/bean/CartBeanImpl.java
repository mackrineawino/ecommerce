package com.ecom.app.bean;
import com.ecom.app.model.entity.ItemCart;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class CartBeanImpl extends GenericBeanImpl<ItemCart> implements CartBeanI {

   
}
