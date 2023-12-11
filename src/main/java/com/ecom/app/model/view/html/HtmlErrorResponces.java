package com.ecom.app.model.view.html;

public class HtmlErrorResponces {
    public static String emptyOrderPage() {
        StringBuilder errResponse = new StringBuilder();
    
        errResponse.append("<div style=\"display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh;\">");
        errResponse.append("<div><i style=\"color: #49A3C8; font-size: 90px;\" class=\"fa fa-archive\" aria-hidden=\"true\"></i></div>");
        errResponse.append("<p style=\"color: #E0588E; font-size: 30px;\">No orders available at the moment.</p>");
        errResponse.append("</div>");
    
        return errResponse.toString();
    }

    public static String emptyCartPage() {
        StringBuilder errResponse = new StringBuilder();
    
        errResponse.append("<div style=\"display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh;\">");
        errResponse.append("<div><i style=\"color: #49A3C8; font-size: 90px;\" class=\"fa fa-cart-plus\" aria-hidden=\"true\"></i></div>");
        errResponse.append("<p style=\"color: #E0588E; font-size: 30px;\">Your Cart is Empty.</p>");
        errResponse.append("<div style=\"\"><a href=\"home\" style=\" text-decoration: none; padding: 10px 25px; color: white; background: #49A3C8; border-radius: 3px; \">START SHOPPING</a></div>");
        errResponse.append("</div>");
    
        return errResponse.toString();
    }

     public static String emptyProductsPage() {
        StringBuilder errResponse = new StringBuilder();
    
        errResponse.append("<div style=\"display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh;\">");
        errResponse.append("<div><i style=\"color: #49A3C8; font-size: 90px;\" class=\"fa fa-product-hunt\" aria-hidden=\"true\"></i></div>");
        errResponse.append("<p style=\"color: #E0588E; font-size: 30px;\">No Available Product.</p>");
        errResponse.append("<div style=\"\"><a href=\"addProduct\" style=\" text-decoration: none; padding: 10px 25px; color: white; background: #49A3C8; border-radius: 3px; \">ADD ITEMS</a></div>");
        errResponse.append("</div>");
    
        return errResponse.toString();
    }
    
}
