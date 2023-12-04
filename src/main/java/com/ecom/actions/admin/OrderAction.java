package com.ecom.actions.admin;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.app.bean.OrderBeanI;
import com.ecom.app.model.entity.Order;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.HtmlErrorResponces;
import com.ecom.app.model.view.html.HtmlView;

@WebServlet("/orders")
public class OrderAction extends HttpServlet {
    @EJB
    OrderBeanI orderBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderBean.list(Order.class);

        if (orders.isEmpty()) {
             String emptyPage = HtmlErrorResponces.emptyOrderPage();
            req.setAttribute("content", emptyPage);
        } else {
            String tableContent = HtmlView.generateAdminTable(orders);
            req.setAttribute("content", tableContent);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/adminPage.jsp");
        dispatcher.forward(req, resp);
    }
     @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("id");

        try {
            Long id = Long.parseLong(productIdString);

            orderBean.delete(Product.class, id);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": true}");

            System.out.println("Item removed successfully");
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
