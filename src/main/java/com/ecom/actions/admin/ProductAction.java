package com.ecom.actions.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.ProductCategory;
import com.ecom.app.model.view.html.AddProductPage;
import com.ecom.database.Database;

@WebServlet("/admin")
public class ProductAction extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            new AddProductPage().renderAddProducts(req, resp);
        } else {
            resp.sendRedirect("./");
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Database database = Database.getDbInstance();
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

        database.getProducts()
                .add(new Product(productId, productName, productDescription, price, availability, category, imageUrl));

        resp.sendRedirect("./admin");
    }

}
