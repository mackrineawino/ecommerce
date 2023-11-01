package com.ecom.app.bean;

import java.util.ArrayList;
import java.util.List;

import com.ecom.app.model.Product;

public class ProductBeanImpl implements ProductBean {

    @Override
    public String productList() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("001", "jordan", "black", 5000, 25, "sports", "https://www.swooshstore.com/images/Nike-Dunk-SB-Low-Heels-w.jpg"));
        products.add(new Product("002", "nike", "blue", 5000, 25, "sports", "https://sun9-70.userapi.com/impg/DQBGU5C4MQpbx1Qt9acGsw1RTi7EaNO4kHd0MA/kaLhgbyOgxU.jpg?size=510x510&quality=96&sign=d2cb33e85050c5eb5a931189cf08c798&type=album"));
        products.add(new Product("003", "boots", "yellow", 5000, 25, "sports", "https://cdn1.ozone.ru/s3/multimedia-8/6380186684.jpg"));
        products.add(new Product("004", "stileto", "pink", 5000, 25, "sports", "https://i.pinimg.com/originals/4b/c2/ab/4bc2ab9d886a6e2895b30cd8b4b2e77c.jpg"));

        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<div >");

        for (Product product : products)
            trBuilder.append(product.displayProducts());

        trBuilder.append("</div>");

        return trBuilder.toString();
    }

    public Product addOrUpdateAccount(Product product) throws Exception {

        return null;
    }

    public void deleteAccount(Product product) {

    }

}
