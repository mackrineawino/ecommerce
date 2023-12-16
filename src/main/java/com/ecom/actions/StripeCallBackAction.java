package com.ecom.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.CDI;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.CartBeanI;
import com.ecom.app.bean.OrderBeanI;
import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.entity.Order;
import com.ecom.app.model.entity.OrderStatus;
import com.ecom.utils.OrderCreationEvent;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
@WebServlet("/stripe/callback")
public class StripeCallBackAction extends HttpServlet {

    @EJB
    OrderBeanI orderBean;

    @EJB
    private CartBeanI cartBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
       
        try (BufferedReader reader = request.getReader()) {
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = reader.read(charBuffer)) != -1) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }

        String stripeCallbackPayload = stringBuilder.toString();
        JsonReader reader = Json.createReader(new StringReader(stripeCallbackPayload));
        JsonObject json = reader.readObject();
        System.out.println("Data===========>");
        System.out.println(json.getJsonObject("data"));

        String stripeSignature = request.getHeader("Stripe-Signature");
        com.stripe.model.Event event;
        try {
            event = Webhook.constructEvent(
                    stripeCallbackPayload,
                    stripeSignature,
                    "whsec_DWlTRWFDORsYWxqZmpj3O6FnZT5zNQA9");

            if ("checkout.session.completed".equals(event.getType())) {
                JsonObject sessionData = json.getJsonObject("data").getJsonObject("object");

                // Retrieve the session to get the payment intent ID
                Session session;
                PaymentIntent paymentIntent;
               
                try {
                    session = Session.retrieve(sessionData.getString("id"));
                    paymentIntent = PaymentIntent.retrieve(session.getPaymentIntent());
                    Long totalAmount = paymentIntent.getAmount();
                    double totalPrice = totalAmount / 100.0;
                    String userEmail = sessionData.getJsonObject("customer_details").getString("email");
                    List<ItemCart> orderItems = cartBean.list(ItemCart.class);

                    Order order = new Order(null, null, userEmail, totalPrice, OrderStatus.PLACED, orderItems);
                    String generatedOrderNumber = orderBean.addOrUpdateAndGetOrderNumber(order);

                    CDI.current().getBeanManager().fireEvent(new OrderCreationEvent(order,generatedOrderNumber ));
                } catch (StripeException e) {
                    e.printStackTrace();
                }
            }

        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        }
    }
}
