package com.ecom.actions.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.ProductBeanImpl;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.ProductCategory;
import com.ecom.app.model.view.html.AddProductPage;

@WebServlet("/addProduct")
public class ProductAction extends HttpServlet {
    ProductBeanImpl productBeanImpl = new ProductBeanImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        new AddProductPage().renderAddProducts(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productIdString = req.getParameter("id");
        String productName = req.getParameter("name");
        String productDescription = req.getParameter("description");
        String priceString = req.getParameter("price");
        String availabilityString = req.getParameter("availability");
        String imageUrl = req.getParameter("imageUrl");
        String categoryString = req.getParameter("category");
        ProductCategory category = null;

        double price = Double.parseDouble(priceString);
        Long productId = Long.parseLong(productIdString);
        category = ProductCategory.valueOf(categoryString);
        int availability = Integer.parseInt(availabilityString);
        try {
            productBeanImpl.addOrUpdateProduct(new Product(productId, productName, productDescription, price, availability, category, imageUrl));
        }catch(Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("./addProduct");
    }

}
