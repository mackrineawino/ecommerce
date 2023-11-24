package com.ecom.app.model.view.html;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ecom.app.model.entity.ItemCart;

public class HtmlTable {

    public static String generateTable(List<? extends Object> models) {
        if (models == null || models.isEmpty()) {
            return StringUtils.EMPTY;
        }
        Field[] fields = models.get(0).getClass().getDeclaredFields();
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append(
            "<table style=\"border-collapse: separate; border-spacing: 20px; margin: 0 auto; width: 80%;\">");
        tableBuilder.append("<tr>");
        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                continue;
            }
    
            tableBuilder.append("<th>").append(field.getAnnotation(HtmlTableColHeader.class).headerLabel()).append("</th>");
        }
        tableBuilder.append("<th>Delete</th></tr>");
    
        for (Object model : models) {
            tableBuilder.append("<tr>");
            for (Field field : fields) {
                if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                    continue;
                }
    
                try {
                    field.setAccessible(true);
                    tableBuilder.append("<td>").append(field.get(model)).append("</td>");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            tableBuilder.append("<td>");
            tableBuilder.append("<button onclick=\"removeItem(event)\" id=\"" + getFieldValue("id", model)
                + "\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">REMOVE</button>");
            tableBuilder.append("</td>");
            tableBuilder.append("</tr>");
        }
    
        tableBuilder.append("</table>");
        return tableBuilder.toString();
    }

      public static String generateSummary(List<ItemCart> cartItems) {
        double totalPrice = 0.0;

        StringBuilder summaryBuilder = new StringBuilder();
        summaryBuilder.append(
                "<div style=\"margin-top: 50px; text-align: center; color: white; background: #292929; height: auto;\">");
        summaryBuilder.append(generateTable(cartItems));

        summaryBuilder.append("<div style=\"margin-bottom: 30px;\">");
        for (ItemCart cartItem : cartItems) {
            totalPrice += cartItem.getPrice();
        }
        summaryBuilder.append("<hr>");
        summaryBuilder.append("<h3>Total: " + totalPrice + "<h3>");
        summaryBuilder.append(
                "<button onclick=\"\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; margin-bottom: 30px; border-radius: 3px;\">BUY NOW</button>")
                .append("</td>");
        summaryBuilder.append("</div>");
        summaryBuilder.append("</div>");

        return summaryBuilder.toString();
    }
    

    private static String getFieldValue(String fieldName, Object model) {
    Class<?> currentClass = model.getClass();

    while (currentClass != null) {
        try {
            Method method = currentClass.getDeclaredMethod("get" + StringUtils.capitalize(fieldName));
            Object value = method.invoke(model);
            return value != null ? value.toString() : "";
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Method not found in the current class, try the superclass
            currentClass = currentClass.getSuperclass();
        }
    }

    // Method not found in the entire class hierarchy
    return "";
}

}
