package com.ecom.app.model.view.html;

public class HtmlStats {
    public static String generateStats(int totalUsers, int totalProducts, int totalOrders, int totalCategories ) {
        StringBuilder html = new StringBuilder();

        // HTML body
        html.append("<div style=\"display: flex; align-items: center; justify-content: center; height: 100vh;\">")
        .append("    <div style=\"display: grid; grid-template-columns: repeat(2, 1fr); gap: 25px; padding: 40px; text-align: center;\">")
        .append("        <a href=\"viewUsers\" id=\"totalUsersCard\" style=\"text-decoration: none; color: black;\">") // Add unique identifier
        .append("            <div class=\"stats-card\" style=\"background-color: white; padding: 40px; padding-left: 100px; padding-right: 100px;  border-radius: 20px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);\">")
        .append("                <h2 style=\"font-size: 30px;\">Total Customers</h2>")
        .append("                <p style=\"font-size: 25px;\">" + totalUsers + "</p>")
        .append("            </div>")
        .append("        </a>")
        .append("        <a href=\"viewAll\" id=\"totalProductsCard\" style=\"text-decoration: none; color: black;\">") // Add unique identifier
        .append("            <div class=\"stats-card\" style=\"background-color: white; padding: 40px; border-radius: 20px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);\">")
        .append("                <h2 style=\"font-size: 30px;\">Total Products</h2>")
        .append("                <p style=\"font-size: 25px;\">" + totalProducts + "</p>")
        .append("            </div>")
        .append("        </a>")
        .append("        <a href=\"orders\" id=\"totalOrdersCard\" style=\"text-decoration: none; color: black;\">") // Add unique identifier
        .append("            <div class=\"stats-card\" style=\"background-color: white; padding: 40px; border-radius: 20px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);\">")
        .append("                <h2 style=\"font-size: 30px;\">Total Orders</h2>")
        .append("                <p style=\"font-size: 25px;\">" + totalOrders + "</p>")
        .append("            </div>")
        .append("        </a>")
        .append("        <a href=\"addProduct\" id=\"totalCategoriesCard\" style=\"text-decoration: none; color: black;\">") // Add unique identifier
        .append("            <div class=\"stats-card\" style=\"background-color: white; padding: 40px; border-radius: 20px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);\">")
        .append("                <h2 style=\"font-size: 30px;\">Total Categories</h2>")
        .append("                <p style=\"font-size: 25px;\">" + totalCategories + "</p>")
        .append("            </div>")
        .append("        </a>")
        .append("    </div>")
        .append("</div>")
        .append("<style>")
        .append("    .stats-card {")
        .append("        transition: transform 0.3s, background-color 0.3s;") // Add transition for smooth effect
        .append("    }")
        .append("    .stats-card:hover {")
        .append("        transform: scale(1.1);") // Increase size on hover
        .append("        background: #E0588E;") // Change color on hover
        .append("    }")
        .append("</style>");

        return html.toString();
    }
}
