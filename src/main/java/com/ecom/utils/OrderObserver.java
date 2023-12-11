package com.ecom.utils;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import com.ecom.app.model.entity.Order;

@Singleton
public class OrderObserver {

    @Inject
    private EmailService emailService;

    public void observeOrderCreatedEvent(@Observes OrderCreationEvent event) {
        Order order = event.getNewOrder();
        try {
            emailService.sendOrderConfirmationEmail(order.getEmail(), order.getOrderNumber());
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
        }
    }
}
