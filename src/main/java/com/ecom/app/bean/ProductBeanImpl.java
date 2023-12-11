package com.ecom.app.bean;

import com.ecom.app.model.entity.Product;


import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class ProductBeanImpl extends GenericBeanImpl<Product> implements ProductBeanI {


}
