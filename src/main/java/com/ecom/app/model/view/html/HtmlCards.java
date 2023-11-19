package com.ecom.app.model.view.html;

import java.lang.reflect.Field;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class HtmlCards {

    public static String generateCards(List<? extends Object> models) {
        if (models == null || models.isEmpty()) {
            return StringUtils.EMPTY;
        }

        StringBuilder cardBuilder = new StringBuilder();

        cardBuilder.append(
                "<div style=\" display: grid; grid-template-columns: repeat(3, 1fr); /* Create 3 columns with equal width */\n"
                        + //
                        "    grid-gap: 30px; /* Add a gap between grid items */\n" + //
                        "    justify-content: center;\">");

        for (Object model : models) {
            Field[] fields = model.getClass().getDeclaredFields();

            cardBuilder.append(
                    "<div class=\"display_product\" style=\"border-radius: 5px; margin-top: 20px; text-align: center; background: #C2D7EB; box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5); transition: all 0.3s; display: inline-block; transform-origin: center;\">");

            for (Field field : fields) {
                if (!field.isAnnotationPresent(HtmlCardAnnotations.class)) {
                    continue;
                }

                HtmlCardAnnotations annotation = field.getAnnotation(HtmlCardAnnotations.class);
                try {
                    field.setAccessible(true);
                    if (annotation.label().equals("Image URL: ")) {
                        cardBuilder.append("<img src='" + field.get(model)
                                + "' alt='card image' style=\"height: 250px; width: 250px;\" >");
                    } else {
                        cardBuilder.append("<h3>").append(annotation.label()).append(field.get(model)).append("</h3>");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            cardBuilder.append("<div style=\"display: flex; justify-content: space-between; margin: 20px 30px;\">");
            cardBuilder.append("<a href=\"/ecommerce/viewMore?productId=" + getFieldValue("productId", model)
                    + "\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">VIEW MORE</a>");
            cardBuilder.append("<button id=\"addToCartButton\" productId=\"" + getFieldValue("productId", model)
                    + "\" productName=\"" + getFieldValue("productName", model) + "\" category=\""
                    + getFieldValue("category", model) + "\" price=\"" + getFieldValue("price", model)
                    + "\" onclick=\"addToCartClick(event)\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">")
                    .append("ADD TO CART").append("</button>");
            cardBuilder.append("</div>");

            cardBuilder.append("</div>");
            cardBuilder.append("<style>");
            cardBuilder.append(".display_product:hover {");
            cardBuilder.append("  box-shadow: 8px 8px 12px rgba(0, 0, 0, 0.7);");
            cardBuilder.append("  transform: scale(1.1);");
            cardBuilder.append("}");
            cardBuilder.append("</style>");
        }
        cardBuilder.append("</div>");

        return cardBuilder.toString();
    }

    private static String getFieldValue(String fieldName, Object model) {
        try {
            Field field = model.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(model);
            return value != null ? value.toString() : "";
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return "";
        }
    }

}
