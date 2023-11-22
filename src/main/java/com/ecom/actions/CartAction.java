package com.ecom.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.CartBeanI;
import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.view.html.HtmlTable;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/addToCart")
public class CartAction extends BaseAction {
    @EJB
    private CartBeanI cartBean;
    private ItemCart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ItemCart> cartItems = cartBean.list(ItemCart.class);

        String cartHtml = HtmlTable.generateSummary(cartItems);

        renderPage(req, resp, 3, cartHtml);

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

        cartBean.addOrUpdate(cart);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"success\": true}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("productId");

        try {
            Long productId = Long.parseLong(productIdString);
            cartBean.delete(productId);

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
