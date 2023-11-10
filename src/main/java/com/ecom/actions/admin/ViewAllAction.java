package com.ecom.actions.admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.AddProductPage;
import com.ecom.database.Database;

@WebServlet("/viewAll")
public class ViewAllAction extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = Database.getDbInstance().getProducts();

      
        StringBuilder tableHtml = new StringBuilder();
        tableHtml.append("<table cellspacing='20' cellpadding='10'>");
     
        tableHtml.append("<tr><th colspan='5' style='text-align: center; font-size: 24px;'>ALL PRODUCTS</th></tr>");
        tableHtml.append("<tr>");
        tableHtml.append("<th>ID</th>");
        tableHtml.append("<th>Name</th>");
        tableHtml.append("<th>Category</th>");
        tableHtml.append("<th>Price</th>");
        tableHtml.append("<th>Action</th>");
        tableHtml.append("</tr>");

        for (Product product : products) {
            tableHtml.append(tableRow(product));
        }

        tableHtml.append("</table>");

      
        String centeredTableHtml = "<div style='display: flex; justify-content: center; align-items: center; height: 100vh;'>" +
                                    tableHtml.toString() +
                                    "</div>";

   
        new AddProductPage().renderAddProducts(req, resp, centeredTableHtml);
    }

    private String tableRow(Product product) {
        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<tr>");
        trBuilder.append("<td>").append(product.getProductId()).append("</td>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(product.getProductName())).append("</td>");
        trBuilder.append("<td>").append(product.getCategory()).append("</td>");
        trBuilder.append("<td>").append(new DecimalFormat("#,###.##").format(product.getPrice())).append("</td>");
         trBuilder.append("<td>").append("<button onclick=\"updateItem(event)\" productId=\"" + product.getProductId() + "\"  style=\"text-decoration: none; padding: 10px 25px; color: white; background: #49A3C8; border-radius: 3px;\">UPDATE</button>").append("</td>");
        trBuilder.append("<td>").append("<button onclick=\"deleteFromDb(event)\" productId=\"" + product.getProductId() + "\"  style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">DELETE</button>").append("</td>");
        trBuilder.append("</tr>");

        return trBuilder.toString();
    }
}
