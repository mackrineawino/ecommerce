package com.ecom.app.model.view.html;

import java.lang.reflect.Field;
import java.util.List;

public class HtmlView {
    public static String generateAdminTable(List<? extends Object> models) {
        StringBuilder tableHtml = new StringBuilder();

        Field[] fields = models.get(0).getClass().getDeclaredFields();
        tableHtml.append(
                "<div style=\"display: flex; justify-content: center; align-items: center; height: 100vh;\"><table cellspacing='20' cellpadding='10'>");
        tableHtml.append("<tr>");
        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                continue;
            }

            tableHtml.append("<th>").append(field.getAnnotation(HtmlTableColHeader.class).headerLabel())
                    .append("</th>");
        }
        tableHtml.append("<th>Action</th>");
        tableHtml.append("<th>Action</th></tr>");
        for (Object model : models) {
            tableHtml.append("<tr>");
            for (Field field : fields) {
                if (!field.isAnnotationPresent(HtmlTableColHeader.class)) {
                    continue;
                }

                try {
                    field.setAccessible(true);
                    tableHtml.append("<td>").append(field.get(model)).append("</td>");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            tableHtml.append("<td>").append("<button onclick=\"updateItem(event)\" productId=\""
                    + getFieldValue(model, "productId")
                    + "\"  style=\"text-decoration: none; padding: 10px 25px; color: white; background: #49A3C8; border-radius: 3px;\">EDIT</button>")
                    .append("</td>");
            tableHtml.append("<td>").append("<button onclick=\"deleteFromDb(event)\" productId=\""
                    + getFieldValue(model, "productId")
                    + "\"  style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">DELETE</button>")
                    .append("</td>");
            tableHtml.append("</tr>");
        }

        tableHtml.append("</table></div>");
        return tableHtml.toString();

    }

    private static Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
