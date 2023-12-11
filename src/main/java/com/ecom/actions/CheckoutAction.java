package com.ecom.actions;
import com.ecom.app.bean.CheckoutBeanI;
import com.stripe.exception.StripeException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutAction extends BaseAction {

    @EJB
    private CheckoutBeanI checkoutBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        try {
            String userEmail = (String) request.getSession().getAttribute("email");
            String sessionId = checkoutBean.createCheckoutSession(totalPrice, userEmail);

            response.getWriter().print("{\"sessionId\": \"" + sessionId + "\"}");

        } catch (StripeException e) {
            response.setStatus(500);
            response.getWriter().print("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}