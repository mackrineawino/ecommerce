package com.ecom.actions;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.OrderBeanI;
import com.ecom.app.model.entity.Order;
import com.ecom.app.model.view.html.HtmlErrorResponces;
import com.ecom.app.model.view.html.HtmlOrderTable;

@WebServlet("/myOrders")
public class ViewOrdersAction extends BaseAction {
    @EJB
    OrderBeanI orderBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderBean.list(Order.class);

        // String userEmail = req.getRemoteUser();
        // List<Order> userOrders = orders.stream()
        //         .filter(order -> userEmail.equals(order.getEmail()))
        //         .collect(Collectors.toList());

        if (orders.isEmpty()) {
            String emptyPage = HtmlErrorResponces.emptyOrderPage();
            renderPage(req, resp, 1, emptyPage);
        } else {
            String orderList = HtmlOrderTable.generateAdminTable(orders);
            renderPage(req, resp, 1, orderList);
        }
    }

}