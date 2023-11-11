package com.ecom.app.model.view.html;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class HtmlTable {


        public static String generateTable(List<? extends Object> models, Map<String, String> fieldToHeaderMapping) {
        if (models == null || models.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("<table style=\"border-collapse: separate; border-spacing: 20px; margin: 0 auto; width: 80%;\">");
        tableBuilder.append("<tr>");
        for (String header : fieldToHeaderMapping.values()) {
            tableBuilder.append("<th>").append(header).append("</th>");
        }
        tableBuilder.append("<th>Delete</th></tr>");

        for (Object model : models) {
            tableBuilder.append("<tr>");
            for (String fieldName : fieldToHeaderMapping.keySet()) {
                tableBuilder.append("<td>").append(getFieldValue(model, fieldName)).append("</td>");
            }
            tableBuilder.append("<td>");
            tableBuilder.append("<button onclick=\"removeItem(event)\" productId=\"" + getFieldValue(model, "productId") + "\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">REMOVE</button>");
            tableBuilder.append("</td>");
            tableBuilder.append("</tr>");
        }

        tableBuilder.append("</table>");
        return tableBuilder.toString();
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
    }}

