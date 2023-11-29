package com.ecom.app.bean;

import com.ecom.app.model.entity.Order;
import com.ecom.utils.OrderNoQualifier;
import com.ecom.utils.TransactionNumberGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class OrderBeanImpl extends GenericBeanImpl<Order> implements OrderBeanI {
    @Inject
    @OrderNoQualifier
    private TransactionNumberGenerator txnNoGenerator;

    @Override
    public void addOrUpdate(Order order) {
        order.setOrderNumber(txnNoGenerator.generate());
        getDao().addOrUpdate(order);

    }
}
