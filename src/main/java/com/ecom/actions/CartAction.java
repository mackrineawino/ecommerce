package com.ecom.actions;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.CartBeanI;
import com.ecom.app.bean.CartBeanImpl;
import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.view.html.AppPage;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/addToCart")
public class CartAction extends BaseAction {
    private CartBeanI cartBean = new CartBeanImpl();
    private ItemCart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new AppPage().renderHtml(req, resp, 0, cartBean.CartProducts());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Read the JSON data from the request's input stream and parse it
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Parse the JSON data into a Java object
        ObjectMapper objectMapper = new ObjectMapper();
        cart = objectMapper.readValue(json.toString(), ItemCart.class);

        // Now, you have the populated 'cart' object, and you can add it to the cart
        cartBean.addItemToCart(cart);

        resp.sendRedirect("./home");
    }
    // Inside your doDelete method in CartAction class
@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String productIdString = req.getParameter("productId");

    try {
        Long productId = Long.parseLong(productIdString);

        // Call the removeItemFromCart method in CartBeanImpl
        cartBean.deleteFromCart(productId);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": true}");

        System.out.println("Item removed successfully");
    } catch (NumberFormatException e) {
        // Handle invalid productId format
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": false, \"error\": \"Bad request\"}");
        System.out.println("Bad request: Invalid productId format");
    } catch (Exception e) {
        // Handle other exceptions
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": false, \"error\": \"Internal server error\"}");
        System.out.println("Internal server error: " + e.getMessage());
    }
}


}
