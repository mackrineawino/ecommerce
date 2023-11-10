package com.ecom.app.bean;


import java.util.Iterator;
import java.util.List;
import com.ecom.app.model.entity.Product;
import com.ecom.database.Database;

public class ProductBeanImpl implements ProductBeanI {
    List<Product> products = Database.getDbInstance().getProducts();

    @Override
    public String productList() {

        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<div style=\" display: grid; grid-template-columns: repeat(3, 1fr); /* Create 3 columns with equal width */\n" + //
        "    grid-gap: 30px; /* Add a gap between grid items */\n" + //
        "    justify-content: center;\">");

        for (Product product : products)
        trBuilder.append(product.displayProducts());

        trBuilder.append("</div>");

        return trBuilder.toString();
    }

    public Product addOrUpdateProduct(Product product){
        products.add(product);
        return product;
    }

    public void deleteProduct(Long productId) {
Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
                iterator.remove();
                return;
            }
        }
    }

}
