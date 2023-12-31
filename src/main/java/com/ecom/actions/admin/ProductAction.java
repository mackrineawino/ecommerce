package com.ecom.actions.admin;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.actions.BaseAction;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.HtmlForm;

@WebServlet("/addProduct")
public class ProductAction extends BaseAction {
    @EJB
    private ProductBeanI productBean;
    private Product product = new Product();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("content", HtmlForm.form(Product.class, null));
        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/adminPage.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serializeForm(product, req.getParameterMap());

        // Validate form values
        if (product.getPrice() <= 0 || product.getAvailability() <= 0) {
            String errorMessage = "Price and Availability Must Be greater than 0.";
            req.setAttribute("content", HtmlForm.form(Product.class, errorMessage));
            RequestDispatcher dispatcher = req.getRequestDispatcher("./app/adminPage.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        productBean.addOrUpdate(product);

        boolean isProductAddedSuccessfully = true;

        if (isProductAddedSuccessfully) {
            req.getSession().setAttribute("productAddSuccess", "Product added successfully!");
        }

        resp.sendRedirect("./viewAll");
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdString = req.getParameter("id");

        try {
            Long id = Long.parseLong(productIdString);

            productBean.delete(Product.class, id);

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
