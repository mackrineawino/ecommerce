package com.ecom.actions;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutAction extends BaseAction {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.setContentType("application/json");
                double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
                Stripe.apiKey = "sk_test_51OGzlhGFDR8x86eFWiamFoTs85XlaIchgB7v8JWGFeV3kDuAPR3umagrsbSh8LyyzE1c42jaKl1ClmNIACZZZRY800xkzQulNU";

                try {
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
                                        .setSuccessUrl("http://localhost:8080/ecommerce/home")
                                        .setCancelUrl("http://localhost:8080/ecommerce/addToCart")
                                        .build();

                        Session session = Session.create(createParams);

                        response.getWriter().print("{\"sessionId\": \"" + session.getId() + "\"}");

                } catch (StripeException e) {
                        response.setStatus(500);
                        response.getWriter().print("{\"error\": \"" + e.getMessage() + "\"}");
                }
        }
}
