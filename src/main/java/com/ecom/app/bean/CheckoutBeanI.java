package com.ecom.app.bean;

import com.stripe.exception.StripeException;

public interface CheckoutBeanI {
    String createCheckoutSession(double totalPrice, String userEmail) throws StripeException;

    // Add this method to your OrderBeanI interface

}
