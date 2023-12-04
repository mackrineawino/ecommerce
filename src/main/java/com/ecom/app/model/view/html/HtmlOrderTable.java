package com.ecom.app.model.view.html;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ecom.app.model.entity.ItemCart;

public class HtmlOrderTable {

    public static String generateAdminTable(List<? extends Object> models) {
        StringBuilder tableHtml = new StringBuilder();
        Field[] fields = models.get(0).getClass().getDeclaredFields();
        tableHtml.append("<div style=\"display: flex; justify-content: center; align-items: center; height: 100vh;\">");
        tableHtml.append(
                "<table style=\"border-collapse: collapse; width: 80%; border: 1px solid #ddd; margin: 20px;\">");
        tableHtml.append("<tr style=\"background-color: #f2f2f2;\">");

        for (Field field : fields) {
            System.out.println("Field Name: " + field.getName());
            System.out.println("Field Type: " + field.getType());

            if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                continue;
            }

            if (field.isAnnotationPresent(HtmlTableColHeader.class) &&
                    field.getAnnotation(HtmlTableColHeader.class).headerLabel().equals("Customer Email")) {
                continue;
            }

            tableHtml.append("<th style=\"border: 1px solid #ddd; padding: 10px;\">" +
                    field.getAnnotation(HtmlTableColHeader.class).headerLabel() + "</th>");
        }

        tableHtml.append("</tr>");

        for (Object model : models) {

            for (Field field : fields) {

                if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                    continue;
                }

                if (field.isAnnotationPresent(HtmlTableColHeader.class) &&
                        field.getAnnotation(HtmlTableColHeader.class).headerLabel().equals("Customer Email")) {
                    continue;
                }

                try {
                    field.setAccessible(true);
                    // if (field.getType().isAssignableFrom(List.class)
                    //         && field.getAnnotation(HtmlTableColHeader.class).headerLabel().equals("Items")) {
                    //     List<ItemCart> orderItems = (List<ItemCart>) field.get(model);
                    //     tableHtml.append("<td style=\"border: 1px solid #ddd; color: white; padding: 10px;\">");
                    //     for (ItemCart item : orderItems) {
                    //         tableHtml.append(item.getProductName()).append(": ").append(item.getPrice());
                    //     }
                    //     tableHtml.append("</td>");
                    // } else {
                        tableHtml.append("<td style=\"border: 1px solid #ddd; color: white; padding: 10px;\">" +
                                field.get(model) + "</td>");
                    
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            tableHtml.append("</tr>");
        }

        tableHtml.append("</table></div>");
        return tableHtml.toString();
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
