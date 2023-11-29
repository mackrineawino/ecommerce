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
import com.ecom.app.model.view.html.HtmlView;

@WebServlet("/orders")
public class OrderAction extends HttpServlet {
    @EJB
    OrderBeanI orderBeanI;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderBeanI.list(Order.class);
        String tableContent = HtmlView.generateAdminTable(orders);
        req.setAttribute("content", tableContent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/adminPage.jsp");
        dispatcher.forward(req, resp);
    }

}
