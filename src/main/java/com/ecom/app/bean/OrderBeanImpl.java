package com.ecom.app.bean;

import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.entity.Order;
import com.ecom.utils.OrderNoQualifier;
import com.ecom.utils.TransactionNumberGenerator;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    @EJB
    private CartBeanI cartBean;

    @Override
    public void addOrUpdate(Order order) {
        order.setOrderNumber(txnNoGenerator.generate());
        getDao().addOrUpdate(order);

    }

    @Override
    public Order findOrderById(Long orderId) {
        try {
            return em
                    .createQuery("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.id = :orderId",
                            Order.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public String addOrUpdateAndGetOrderNumber(Order order) {
        List<ItemCart> cartItems = cartBean.list(ItemCart.class);
        // Generate order number
        order.setOrderNumber(txnNoGenerator.generate());

        // Persist the order
        getDao().addOrUpdate(order);

        // Flush the EntityManager to synchronize changes with the database
        em.flush();
        System.out.println("oredrnumber--------" + order.getOrderNumber());
        // Retrieve the persisted order with its ID
        Order foundOrder = findOrderByOrderNumber(order.getOrderNumber());
        System.out.println("found--------" + foundOrder.getId());
        if (foundOrder != null) {
            // Associate the order with ItemCart instances
            for (ItemCart cartItem : cartItems) {
                cartItem.setOrder(foundOrder);
                foundOrder.getOrderItems().add(cartItem);

            }

            // Persist the updated ItemCart instances
            cartBean.addOrUpdateCartItems(cartItems);

            return foundOrder.getOrderNumber();
        } else {
            return null;
        }
    }

    public Order findOrderByOrderNumber(String orderNumber) {
        try {
            return em
                    .createQuery(
                            "SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.orderNumber = :orderNumber",
                            Order.class)
                    .setParameter("orderNumber", orderNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no order is found
            // You might want to throw an exception or log a message
            return null;
        }
    }

    @Override
    public Order getBySessionId(String sessionId) {
        String queryString = "SELECT o FROM Order o WHERE o.stripeSessionId = :sessionId";
        Query query = em.createQuery(queryString, Order.class);
        query.setParameter("sessionId", sessionId);

        return (Order) query.getSingleResult();
    }
}
