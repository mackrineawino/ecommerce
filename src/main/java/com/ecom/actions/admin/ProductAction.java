package com.ecom.actions.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.ProductCategory;
import com.ecom.database.Database;

@WebServlet("/admin")
public class ProductAction extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         PrintWriter print = resp.getWriter();
         print.write("<!DOCTYPE html>\n" + //
                 "<html>\n" + //
                 "<head>\n" + //
                 "    <title>Product Entry Form</title>\n" + //
                 "    <style>\n" + //
                 "        label, input {\n" + //
                 "            display: inline-block;\n" + //
                 "            margin-right: 50px; /* Adjust as needed for spacing */\n" + //
                 "        }\n" + //
                 "    </style>\n" + //
                 "</head>\n" + //
                 "<body>\n" + //
                 "<div style=\"display: flex; flex-direction: column; height: 100vh; width: 300px background-color: #9FA6AE;\">" +
                 "<h2>ADMIN DASHBOARD</h2>" +
                 "<ul style=\" display: block; list-style-type: none; \" class=\"navbar\">\n" + //
                 "        <li class=\"navitem\"><a href=\"#\" style=\"text-decoration: none; padding: 20px 14px;\">ADD PRODUCT</a></li>\n" + //
                 "        <li class=\"navitem\"><a href=\"#\" style=\"text-decoration: none; padding: 14px 16px;\">DELETE PRODUCT</a></li>\n" + //
                 "        <li class=\"navitem\"><a href=\"#\" style=\"text-decoration: none; padding: 14px 16px;\">UPDATE PRODUCT</a></li>\n" + //
                 "        <li class=\"navitem\"><a href=\"#\" style=\"text-decoration: none; padding: 14px 16px;\">VIEW ALL</a></li>\n" + //
                 "    </ul>" +
                 "</div>" +
                 "<div>" +
                 "    <h3>ADD PRODUCTS</h3>\n" + //
                 "    <form action=\"./admin\" method=\"post\">\n" + //
                 "        <label for=\"id\">Product ID:</label>\n" + //
                 "        <input type=\"text\" id=\"id\" name=\"id\" required>\n" + //
                 "\n" + //
                 "        <label for=\"name\">Product Name:</label>\n" + //
                 "        <input type=\"text\" id=\"name\" name \"name\" required><br><br>\n" + //
                 "\n" + //
                 "        <label for=\"description\">Description:</label>\n" + //
                 "        <textarea id=\"description\" name=\"description\" required></textarea>\n" + //
                 "\n" + //
                 "        <label for=\"price\">Product Price:</label>\n" + //
                 "        <input type=\"text\" id=\"price\" name=\"price\" required><br><br>\n" + //
                 "\n" + //
                 "        <label for=\"availability\">Availability:</label>\n" + //
                 "        <input type=\"text\" id=\"availability\" name=\"availability\" required>\n" + //
                 "\n" + //
                 "        <label for=\"category\">Product Category:</label>\n" + //
                 "        <select id=\"category\" name=\"category\" required>\n" + //
                 "            <option value=\"Electronics\">Electronics</option>\n" + //
                 "            <option value=\"Clothing\">Clothing</option>\n" + //
                 "            <option value=\"Food\">Food</option>\n" + //
                 "            <!-- Add more category options as needed -->\n" + //
                 "        </select><br><br>\n" + //
                 "\n" + //
                 "        <label for=\"imageUrl\">Image URL:</label>\n" + //
                 "        <input type=\"text\" id=\"imageUrl\" name=\"imageUrl\" required>\n" + //
                 "\n" + //
                 "        <input type=\"submit\" value=\"Submit\">\n" + //
                 "    </form>\n" + //
                 "</div>" +
                 "</body>\n" + //
                 "</html>\n" + //
                 "");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Database database = Database.getDbInstance();

        String productName = req.getParameter("productName");
        String productDescription = req.getParameter("productDescription");
        String priceString = req.getParameter("price");
        String availabilityString=req.getParameter("availability");
        String imageUrl=req.getParameter("imageUrl");
        String categoryString=req.getParameter("category");

         Random random = new Random();
         ProductCategory category=null;

        double price = Double.parseDouble(priceString);
        category=ProductCategory.valueOf(categoryString);
        int availability=Integer.parseInt(availabilityString);

        database.getProducts().add(new Product(random.nextLong(), productName, productDescription, price, availability, category, imageUrl));

        resp.sendRedirect("./admin");


    }
}


