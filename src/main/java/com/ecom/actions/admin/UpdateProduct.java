// package com.ecom.actions.admin;

// import java.io.IOException;
// import java.util.List;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.ecom.actions.BaseAction;
// import com.ecom.app.model.entity.Product;
// import com.ecom.app.model.entity.ProductCategory;
// import com.ecom.database.Database;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @WebServlet("/productData")
// public class UpdateProduct extends BaseAction {
//     List<Product> products = Database.getDbInstance().getProducts();

//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         Long productId = Long.parseLong(req.getParameter("productId"));
//         Product product = findProductById(productId);

//         if (product != null) {

//             String productJson = convertProductToJson(product);

//             resp.setContentType("application/json");
//             resp.setCharacterEncoding("UTF-8");
//             resp.getWriter().write(productJson);
//         } else {

//             resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//         }
//     }

//     private String convertProductToJson(Product product) {

//         ObjectMapper objectMapper = new ObjectMapper();
//         try {
//             return objectMapper.writeValueAsString(product);
//         } catch (JsonProcessingException e) {
//             e.printStackTrace();
//             return "{}";
//         }
//     }

//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         Long productId = Long.parseLong(req.getParameter("productId"));
//         String productName = req.getParameter("productName");
//         String category = req.getParameter("category");
//         String price = req.getParameter("price");
//         String availability = req.getParameter("availability");
//         String imageUrl = req.getParameter("imageUrl");
//         String productDescription = req.getParameter("productDescription");

//         Product updatedProduct = findProductById(productId);

//         if (updatedProduct != null) {
//             updatedProduct.setProductName(productName);
//             updatedProduct.setCategory(ProductCategory.valueOf(category));
//             updatedProduct.setPrice(Double.parseDouble(price));
//             updatedProduct.setAvailability(Integer.parseInt(availability));
//             updatedProduct.setImageUrl(imageUrl);
//             updatedProduct.setProductDescription(productDescription);

//             updateProduct(updatedProduct);
//             resp.sendRedirect("/viewAll");
//         } else {
//             resp.sendRedirect("/error-page");
//         }
//     }

//     private Product findProductById(Long productId) {

//         for (Product product : products) {
//             if (product.getProductId().equals(productId)) {
//                 return product;
//             }
//         }

//         return null;
//     }

//     private void updateProduct(Product product) {
//         Database.getDbInstance().updateProduct(product);
//     }

// }
