package com.ecom.app.model.view.html;

import java.lang.reflect.Field;

public class HtmlForm {
    public static String form(Class<?> model) {
        String htmlForm = "<h3 style=\"text-align: center;\">ADD ITEMS</h3>\n" +
                "<form action=\"./addProduct\" method=\"post\">\n";

        Field[] fields = model.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if ("productDescription".equals(fieldName)) {
                htmlForm += "<label for=\"" + fieldName + "\">" + fieldName + ":</label><br>\n";
                htmlForm += "<textarea style=\"background-color: transparent; outline: black; width: 90%; height: 50px; border-radius: 5px; \" id=\"" + fieldName + "\" name=\"" + fieldName + "\" required></textarea><br><br>\n";
            } else if ("category".equals(fieldName)) {
                htmlForm += "<label for=\"" + fieldName + "\">Product Category:</label>\n";
                htmlForm += "<select id=\"" + fieldName + "\" name=\"" + fieldName + "\" required>\n" +
                        "<option value=\"SNEAKER\">Sneaker</option>\n" +
                        "<option value=\"BOOT\">Boot</option>\n" +
                        "<option value=\"DOLL_SHOE\">Doll shoe</option>\n" +
                        "<option value=\"STILETTO\">Stiletto</option>\n" +
                        "</select></br></br>";
            } else {
                htmlForm += "<label for=\"" + fieldName + "\">" + fieldName + ":</label><br>\n";
                htmlForm += "<input type=\"text\" id=\"" + fieldName + "\" name=\"" + fieldName + "\" required><br><br>\n";
            }
        }
        htmlForm += "<input style=\"background: #49A3C8; height: 35px; width: 90%;\" type=\"submit\" value=\"Submit\">\n";
        htmlForm += "</form>\n";

        return htmlForm;
    }
}
