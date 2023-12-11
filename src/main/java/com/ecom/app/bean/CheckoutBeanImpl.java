package com.ecom.app.bean;

import com.ecom.app.bean.CartBeanI;
import com.ecom.app.bean.OrderBeanI;
import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.entity.Order;
import com.ecom.app.model.entity.OrderStatus;
import com.ecom.utils.OrderCreationEvent;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Stateless
public class CheckoutBeanImpl implements CheckoutBeanI {
        @PersistenceContext
        private EntityManager em;

        @EJB
        private CartBeanI cartBean;

        @EJB
        private OrderBeanI orderBean;

        public String createCheckoutSession(double totalPrice, String userEmail) throws StripeException {
                // Create the Stripe session
                Stripe.apiKey = "sk_test_51OGzlhGFDR8x86eFWiamFoTs85XlaIchgB7v8JWGFeV3kDuAPR3umagrsbSh8LyyzE1c42jaKl1ClmNIACZZZRY800xkzQulNU";
                SessionCreateParams createParams = SessionCreateParams.builder()
                                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                                .setPaymentIntentData(SessionCreateParams.PaymentIntentData.builder()
                                                .setSetupFutureUsage(
                                                                SessionCreateParams.PaymentIntentData.SetupFutureUsage.ON_SESSION)
                                                .build())
                                .addLineItem(SessionCreateParams.LineItem.builder()
                                                .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                                                .setCurrency("kes")
                                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                                                .builder()
                                                                                .setName("CoolStuff")
                                                                                .build())
                                                                .setUnitAmount((long) (totalPrice * 100))
                                                                .build())
                                                .setQuantity(1L)
                                                .build())
                                .setMode(SessionCreateParams.Mode.PAYMENT)
                                .setSuccessUrl("http://localhost:8080/ecommerce/myOrders")
                                .setCancelUrl("http://localhost:8080/ecommerce/addToCart")
                                .build();

                Session session = Session.create(createParams);

                return session.getId();

        }

}
