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
    
}
