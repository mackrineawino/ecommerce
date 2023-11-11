package com.ecom.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.ProductCategory;
import com.ecom.database.Database;

@WebServlet("/category")
public class CategoryAction extends BaseAction {
    List<Product> products = Database.getDbInstance().getProducts();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 1, displayCategory());

    }

    private String displayCategory() {
        List<Product> boots = new ArrayList<>();
        List<Product> sneakers = new ArrayList<>();
        List<Product> stiletto = new ArrayList<>();
        List<Product> dollShoes = new ArrayList<>();

        for (Product product : products) {
            ProductCategory category = product.getCategory();
            if (category == ProductCategory.BOOT) {
                boots.add(product);
            } else if (category == ProductCategory.SNEAKER) {
                sneakers.add(product);
            } else if (category == ProductCategory.STILETTO) {
                stiletto.add(product);
            } else if (category == ProductCategory.DOLL_SHOE) {
                dollShoes.add(product);
            }
        }

        StringBuilder categoryDiv = new StringBuilder();
        categoryDiv.append("<div >");
        categoryDiv.append("<div>");
        categoryDiv.append("<h2 style=\"color: white;\">BOOTS</h2>");
        categoryDiv.append(
                "<div style=\" display: grid; grid-template-columns: repeat(3, 1fr); /* Create 3 columns with equal width */\n"
                        + //
                        "    grid-gap: 30px; /* Add a gap between grid items */\n" + //
                        "    justify-content: center;\">");
        for (Product product : boots) {
            categoryDiv.append(product.displayProducts());
        }
        categoryDiv.append("</div>");
        categoryDiv.append("</div>");
        categoryDiv.append("<div>");
        categoryDiv.append("<h2 style=\"color: white;\">SNEAKERS</h2>");
        categoryDiv.append(
                "<div style=\" display: grid; grid-template-columns: repeat(3, 1fr); /* Create 3 columns with equal width */\n"
                        + //
                        "    grid-gap: 30px; /* Add a gap between grid items */\n" + //
                        "    justify-content: center;\">");
        for (Product product : sneakers) {
            categoryDiv.append(product.displayProducts());
        }
        categoryDiv.append("</div>");
        categoryDiv.append("</div>");
        categoryDiv.append("<div>");
        categoryDiv.append("<h2 style=\"color: white;\">STILETTO</h2>");
        categoryDiv.append(
                "<div style=\" display: grid; grid-template-columns: repeat(3, 1fr); /* Create 3 columns with equal width */\n"
                        + //
                        "    grid-gap: 30px; /* Add a gap between grid items */\n" + //
                        "    justify-content: center;\">");
        for (Product product : stiletto) {
            categoryDiv.append(product.displayProducts());
        }
        categoryDiv.append("</div>");
        categoryDiv.append("</div>");
        categoryDiv.append("<div>");
        categoryDiv.append("<h2 style=\"color: white;\">DOLL SHOES</h2>");
        categoryDiv.append(
                "<div style=\" display: grid; grid-template-columns: repeat(3, 1fr); /* Create 3 columns with equal width */\n"
                        + //
                        "    grid-gap: 30px; /* Add a gap between grid items */\n" + //
                        "    justify-content: center;\">");
        for (Product product : dollShoes) {
            categoryDiv.append(product.displayProducts());
        }
        categoryDiv.append("</div>");
        categoryDiv.append("</div>");
        categoryDiv.append("</div>");

        return categoryDiv.toString();
    }
}
