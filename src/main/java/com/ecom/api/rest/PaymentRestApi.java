package com.ecom.api.rest;

import com.ecom.app.bean.CheckoutBeanI;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/checkout")
public class PaymentRestApi {

    @EJB
    private CheckoutBeanI checkoutBean;

    @POST
    @Path("/session")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> createCheckoutSession(Map<String, Object> requestData) {
        try {
            // Check if the "totalPrice" and "userEmail" keys are present
            if (!requestData.containsKey("totalPrice") || !requestData.containsKey("userEmail")) {
                throw new WebApplicationException("Invalid request data. Both 'totalPrice' and 'userEmail' are required.", Response.Status.BAD_REQUEST);
            }
    
            // Retrieve values from the map
            double totalPrice = ((Integer) requestData.get("totalPrice")).doubleValue();

            String userEmail = (String) requestData.get("userEmail");
    
    
            // Rest of your code
            String sessionId = checkoutBean.createCheckoutSession(totalPrice, userEmail);
    
            Map<String, String> response = new HashMap<>();
            response.put("sessionId", sessionId);
    
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebApplicationException("Failed to create checkout session", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}

