package com.ecom.app.bean;
import java.util.Iterator;
import java.util.List;

import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.view.html.HtmlTable;
import com.ecom.database.Database;

public class CartBeanImpl implements CartBeanI {
    private List<ItemCart> cartItems = Database.getDbInstance().getCartItems();
    double totalPrice = 0.0;

    @Override
    public String CartProducts() {
        totalPrice = 0.0;
        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append(
                "<div style=\"margin-top: 50px; text-align: center; color: white; background: #292929; height: auto;\">");
        trBuilder.append(HtmlTable.generateTable(cartItems));

        trBuilder.append("<div style=\"margin-bottom: 30px;\">");
        for (ItemCart cartItem : cartItems) {
            totalPrice += cartItem.getPrice();
        }
        trBuilder.append("<hr>");
        trBuilder.append("<h3>Total: " + totalPrice + "<h3>");
        trBuilder.append(
                "<button onclick=\"\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; margin-bottom: 30px; border-radius: 3px;\">BUY NOW</button>")
                .append("</td>");
        trBuilder.append("</div>");
        trBuilder.append("</div>");
        return trBuilder.toString();
    }
    

    @Override
    public ItemCart addItemToCart(ItemCart item) {
        cartItems.add(item);
        return item;
    }

    public void deleteFromCart(Long productId) {
        Iterator<ItemCart> iterator = cartItems.iterator();
        while (iterator.hasNext()) {
            ItemCart cartItem = iterator.next();
            if (cartItem.getProductId().equals(productId)) {
                totalPrice -= cartItem.getPrice();
                iterator.remove();
                return;
            }
        }
    }
}
