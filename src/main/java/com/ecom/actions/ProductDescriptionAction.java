package com.ecom.actions;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.bean.ProductBeanImpl;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.AppPage;
import com.ecom.database.Database;

@WebServlet(urlPatterns = { "/viewMore/*" })
public class ProductDescriptionAction extends HttpServlet {
    ProductBeanI productBean = new ProductBeanImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Servlet Path: " + request.getServletPath());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println(request.getParameter("productId"));
        Long productId = Long.parseLong(request.getParameter("productId"));

        Product product = getProductById(productId);
        new AppPage().renderHtml(request, response, 0, product.displayProductDescription());

    }

    private Product getProductById(Long productId) {
        List<Product> products = Database.getDbInstance().getProducts();

    for (Product product : products) {
        if (product.getProductId().equals(productId)) {
            return product;
        }
    }

    // If the product is not found, you can return null or throw an exception.
    // For simplicity, we'll return null here.
    return null;
    }

}
