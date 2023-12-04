package com.ecom.app.model.view.html;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class HtmlView {
    public static String generateAdminTable(List<? extends Object> models) {
        StringBuilder tableHtml = new StringBuilder();
        String modelName = models.get(0).getClass().getSimpleName();
        tableHtml.append("<div style=\"text-align: center; margin-top: 20px;\">")
        .append("<h1>").append(modelName).append(" List </h1>")
        .append("</div>");
        Field[] fields = models.get(0).getClass().getDeclaredFields();
        tableHtml.append("<div style=\"display: flex; justify-content: center; align-items: center; height: 100vh; margin-top: 20px;\">");
        tableHtml.append("<table style=\"border-collapse: collapse; width: 80%; border: 1px solid #ddd; margin: 20px;\">");
        tableHtml.append("<tr style=\"background-color: #f2f2f2;\">");

        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                continue;
            }

            tableHtml.append("<th style=\"border: 1px solid #ddd; padding: 10px;\">")
                    .append(field.getAnnotation(HtmlTableColHeader.class).headerLabel()).append("</th>");
        }

        tableHtml.append("<th style=\"border: 1px solid #ddd; padding: 10px;\">Action</th>");
        tableHtml.append("<th style=\"border: 1px solid #ddd; padding: 10px;\">Action</th></tr>");

        for (Object model : models) {
            tableHtml.append("<tr style=\"border: 1px solid #ddd;\">");

            for (Field field : fields) {
                if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                    continue;
                }

                try {
                    field.setAccessible(true);
                    tableHtml.append("<td style=\"border: 1px solid #ddd; padding: 10px;\">")
                            .append(field.get(model)).append("</td>");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            tableHtml.append("<td style=\"border: 1px solid #ddd; padding: 10px;\">")
                    .append("<button onclick=\"updateItem(event)\" id=\"" + getFieldValue("id", model)
                            + "\"  style=\"text-decoration: none; padding: 10px 25px; color: white; background: #49A3C8; border-radius: 3px;\">EDIT</button>")
                    .append("</td>");

            tableHtml.append("<td style=\"border: 1px solid #ddd; padding: 10px;\">")
                    .append("<button onclick=\"deleteFromDb(event)\" id=\"" + getFieldValue("id", model)
                            + "\"  style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">DELETE</button>")
                    .append("</td>");

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

    public static String addButton() {
        StringBuilder button=new StringBuilder();
         button.append("<div style=\" margin-top: 100px; margin-left: 100px; margin-bottom: -130px;\"><a href=\"addProduct\" style=\" text-decoration: none; padding: 10px 25px; color: white; background: #49A3C8; border-radius: 3px; \">ADD ITEM</a></div>");
        return button.toString();
    }
}
