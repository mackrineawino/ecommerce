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
import com.ecom.app.bean.UserBeanI;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.app.model.view.html.HtmlView;

@WebServlet("/viewUsers")
public class ViewUsersAction extends HttpServlet{
    @EJB
    UserBeanI userBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userBean.list(User.class);
        List<User> normalUsers = users.stream()
                .filter(user -> user.getUserType() == UserType.NORMAL_USER)
                .collect(Collectors.toList());
        String tableContent = HtmlView.generateAdminTable(normalUsers);
        req.setAttribute("content", tableContent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/adminPage.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("id");

        try {
            Long id = Long.parseLong(productIdString);

            userBean.delete(Product.class, id);

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
