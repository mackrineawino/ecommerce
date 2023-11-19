package com.ecom.events;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.ProductCategory;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.database.Database;

@WebListener
public class AppInitialization implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
                System.out.println("*************** Initializing database *************");
                Database database = Database.getDbInstance();

                // database.getCartItems().add(new ItemCart( 1L,"nike", ProductCategory.BOOT, 333));
                //  database.getCartItems().add(new ItemCart(2L, "nike", ProductCategory.BOOT, 333));


                database.getData().add(new User(01L, "hillary@test.com", "1234", UserType.NORMAL_USER));
                database.getData().add(new User(02L, "sedah@test.com", "123", UserType.NORMAL_USER));
                database.getData().add(new User(03L, "chloe@test.com", "12345", UserType.ADMIN));

                database.getData().add(new Product(1L, "jordan", "black", 1500, 25, ProductCategory.SNEAKER,
                                "https://github.com/mackrineawino/images/blob/main/805sa-pink-40-saheb-pink-original-imafghzfctaf4xdy-removebg-preview.png?raw=true"));
                database.getData().add(new Product(2L, "nike", "blue", 3000, 25, ProductCategory.SNEAKER,
                                "https://github.com/mackrineawino/images/blob/main/kaLhgbyOgxU-removebg-preview.png?raw=true"));
                database.getData().add(new Product(3L, "boots", "yellow", 1500, 25, ProductCategory.BOOT,
                                "https://github.com/mackrineawino/images/blob/main/6380186684-removebg-preview.png?raw=true"));
                database.getData().add(new Product(5L, "stileto", "pink", 2000, 25, ProductCategory.STILETTO,
                                "https://github.com/mackrineawino/images/blob/main/4bc2ab9d886a6e2895b30cd8b4b2e77c-removebg-preview.png?raw=true"));
                database.getData().add(new Product(6L, "Doll", "pink", 2500, 25, ProductCategory.DOLL_SHOE,
                                "https://github.com/mackrineawino/images/blob/main/1aa_f30b5dc1-d535-4403-bc1d-af9693c8e48d-removebg-preview.png?raw=true"));

                database.getData().add(new Product(004L, "Boots", "pink", 5000, 25, ProductCategory.BOOT,
                                "https://github.com/mackrineawino/images/blob/main/Nike-Dunk-SB-Low-Heels-w-removebg-preview%20(2).png?raw=true"));

        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
                System.out.println("Application is undeployed or destroyed");
        }

}
