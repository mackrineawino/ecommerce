package com.ecom.app.bean;

import com.ecom.app.model.entity.Order;

public interface OrderBeanI extends GenericBeanI<Order>{
    Order getBySessionId(String sessionId);
}
