package com.ecom.actions.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.app.bean.OrderBeanI;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.bean.UserBeanI;
import com.ecom.app.model.entity.Order;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.app.model.view.html.HtmlStats;

@WebServlet("/adminhome")
public class AdminHomeAction extends HttpServlet {
  @EJB
  OrderBeanI orderBean;
  @EJB
  ProductBeanI productBean;
  @EJB
  UserBeanI userBean;

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Order> orders = orderBean.list(Order.class);
    List<Product> products = productBean.list(Product.class);
    List<User> users = userBean.list(User.class);

    List<User> normalUsers = users.stream()
        .filter(user -> user.getUserType() == UserType.NORMAL_USER)
        .collect(Collectors.toList());

    int orderSize = orders.size();
    int productSize = products.size();
    int userSize = normalUsers.size();
    String tableContent = HtmlStats.generateStats(userSize, productSize, orderSize, 4);
    req.setAttribute("content", tableContent);
    RequestDispatcher dispatcher = req.getRequestDispatcher("./app/adminPage.jsp");
    dispatcher.forward(req, resp);
  }
}
