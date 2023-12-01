// package com.ecom.app.model.view.html;

// import java.util.ArrayList;
// import java.util.List;

// import com.ecom.app.bean.ProductBeanI;
// import com.ecom.app.bean.ProductBeanImpl;
// import com.ecom.app.model.entity.Product;
// import com.ecom.app.model.entity.ProductCategory;

// public class Category {
//     static ProductBeanI productBean = new ProductBeanImpl();
//     static List<Product> products = productBean.list(Product.class);

//     public static String displayCategory(List<? extends Object> models) {
//         List<Product> boots = new ArrayList<>();
//         List<Product> sneakers = new ArrayList<>();
//         List<Product> stiletto = new ArrayList<>();
//         List<Product> dollShoes = new ArrayList<>();

//         for (Product product : products) {
//             ProductCategory category = product.getCategory();
//             if (category == ProductCategory.BOOT) {
//                 boots.add(product);
//             } else if (category == ProductCategory.SNEAKER) {
//                 sneakers.add(product);
//             } else if (category == ProductCategory.STILETTO) {
//                 stiletto.add(product);
//             } else if (category == ProductCategory.DOLL_SHOE) {
//                 dollShoes.add(product);
//             }
//         }

//         StringBuilder categoryDiv = new StringBuilder();
//         categoryDiv.append("<div >");

//         categoryDiv.append("<div>");
//         categoryDiv.append("<h2 style=\"color: white;\">BOOTS</h2>");
//         categoryDiv.append(HtmlCards.generateCards(boots));
//         categoryDiv.append("</div>");

//         categoryDiv.append("<div>");
//         categoryDiv.append("<h2 style=\"color: white;\">SNEAKERS</h2>");
//         categoryDiv.append(HtmlCards.generateCards(sneakers));
//         categoryDiv.append("</div>");

//         categoryDiv.append("<div>");
//         categoryDiv.append("<h2 style=\"color: white;\">STILETTO</h2>");
//         categoryDiv.append(HtmlCards.generateCards(stiletto));
//         categoryDiv.append("</div>");

//         categoryDiv.append("<div>");
//         categoryDiv.append("<h2 style=\"color: white;\">DOLL SHOES</h2>");
//         categoryDiv.append(HtmlCards.generateCards(dollShoes));
//         categoryDiv.append("</div>");
//         categoryDiv.append("</div>");

//         return categoryDiv.toString();
//     }
// }
