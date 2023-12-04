package com.ecom.app.model.view.html;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

            cardBuilder.append("<a href=\"/ecommerce/viewMore?id=" + getFieldValue("id", model)
                    + "\" style=\"text-decoration: none; padding: 10px 25px; color: white; background: #E0588E; border-radius: 3px;\">VIEW MORE</a>");
            cardBuilder.append("<button  id=\"" + getFieldValue("id", model)
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
// public static String slider() {
//     List<String> imageUrls = Arrays.asList(
//         "https://github.com/mackrineawino/images/blob/main/shoe4.JPG?raw=true", 
//         "https://github.com/mackrineawino/images/blob/main/kaLhgbyOgxU-removebg-preview.png?raw=true", 
//         "https://github.com/mackrineawino/images/blob/main/image-removebg-preview%20(5).png?raw=true", 
//         "https://github.com/mackrineawino/images/blob/main/image-removebg-preview%20(4).png?raw=true",
//         "https://github.com/mackrineawino/images/blob/main/image-removebg-preview%20(3).png?raw=true", 
//         "https://github.com/mackrineawino/images/blob/main/air-jordan-1-retro-high-og-unc-555088-134-pair.jpg?raw=true", 
//         "https://raw.githubusercontent.com/mackrineawino/images/main/air-jordan-1-dark-mocha-release-date-555088-105-pair.jpeg.webp", 
//         "https://github.com/mackrineawino/images/blob/main/Nike-Dunk-SB-Low-Heels-w-removebg-preview%20(2).png?raw=true"
//     );

//     StringBuilder sliderHtml = new StringBuilder();

//     sliderHtml.append("<div class=\"slider-container\" style=\"background: white; margin-top: 10px; margin-bottom: 50px;\">")
//             .append("<div class=\"slider\">");

//     int cardsPerSlide = 4;

//     for (int i = 0; i < imageUrls.size(); i += cardsPerSlide) {
//         sliderHtml.append("<div class=\"slide\">"); // Each slide div

//         for (int j = i; j < i + cardsPerSlide && j < imageUrls.size(); j++) {
//             String imageUrl = imageUrls.get(j);

//             sliderHtml.append("<div class=\"display_product\" style=\"margin-right: 10px; border-radius: 5px; text-align: center; background: #C2D7EB; box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5); transition: transform 0.3s; display: inline-block; transform-origin: center;\">");

//             // Display image
//             sliderHtml.append("<div class=\"image-container\">")
//                     .append("<img src='" + imageUrl + "' alt='card image' style=\"height: 250px; width: 250px;\" >")
//                     .append("</div>");

//             sliderHtml.append("</div>");
//         }

//         sliderHtml.append("</div>");
//     }

//     sliderHtml.append("</div>")
//             .append("<div id=\"prevButton\" class=\"slider-button\" style=\"color: #E0588E; background: #1A1D16; border-radius: 50%; margin-left: 53px; position: absolute; top: 50%; left: 0; transform: translateY(-50%);\"><span style=\" font-size: 50px;  font-weight: bold;\" class=\"material-symbols-outlined\">\n" + //
//                     "arrow_back_ios\n" + //
//                     "</span></div>")
//             .append("<div id=\"nextButton\" class=\"slider-button\" style=\"color: #E0588E; background: #1A1D16; border-radius: 50%;  margin-right: 50px; position: absolute; top: 50%; right: 0; transform: translateY(-50%);\"><span style=\" font-size: 50px;  font-weight: bold;\" class=\"material-symbols-outlined\">arrow_forward_ios</span></div>")
//             .append("</div>")
//             .append("<script src=\"js/Slider.js\"></script>");

//     return sliderHtml.toString();
// }



}
