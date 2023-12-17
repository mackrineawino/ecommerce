package com.ecom.app.bean;

import com.ecom.app.model.entity.Order;
import com.ecom.utils.OrderNoQualifier;
import com.ecom.utils.TransactionNumberGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Remote
public class OrderBeanImpl extends GenericBeanImpl<Order> implements OrderBeanI {
    @Inject
    @OrderNoQualifier
    private TransactionNumberGenerator txnNoGenerator;

     @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addOrUpdate(Order order) {
        order.setOrderNumber(txnNoGenerator.generate());
        getDao().addOrUpdate(order);

    }

    @Override
public Order findOrderById(Long orderId) {
    try {
        return em.createQuery("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.id = :orderId", Order.class)
            .setParameter("orderId", orderId)
            .getSingleResult();
    } catch (Exception e) {
      
        e.printStackTrace();
        return null;
    }
}

    

    public String addOrUpdateAndGetOrderNumber(Order order) {
        order.setOrderNumber(txnNoGenerator.generate());
        getDao().addOrUpdate(order);
        return order.getOrderNumber();
    }
    @Override
    public Order getBySessionId(String sessionId) {
        String queryString = "SELECT o FROM Order o WHERE o.stripeSessionId = :sessionId";
        Query query = em.createQuery(queryString, Order.class);
        query.setParameter("sessionId", sessionId);

        return (Order) query.getSingleResult();
    }
}
