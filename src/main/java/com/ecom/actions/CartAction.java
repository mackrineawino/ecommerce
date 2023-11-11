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
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/addToCart")
public class CartAction extends BaseAction {
    private CartBeanI cartBean = new CartBeanImpl();
    private ItemCart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        renderPage(req, resp, 3, cartBean.CartProducts());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        cart = objectMapper.readValue(json.toString(), ItemCart.class);

        cartBean.addItemToCart(cart);

        resp.sendRedirect("./home");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");

        try {
            Long productId = Long.parseLong(productIdString);

            cartBean.deleteFromCart(productId);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": true}");

        } catch (NumberFormatException e) {

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": false, \"error\": \"Bad request\"}");
            System.out.println("Bad request: Invalid productId format");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": false, \"error\": \"Internal server error\"}");
            System.out.println("Internal server error: " + e.getMessage());
        }
    }

}
