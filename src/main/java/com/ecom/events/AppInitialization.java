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

        database.getUsers().add(new User(01L, "hillary@test.com", "1234", UserType.NORMAL_USER));
        database.getUsers().add(new User(02L, "sedah@test.com", "123", UserType.NORMAL_USER));
        database.getUsers().add(new User(03L, "chloe@test.com", "12345", UserType.ADMIN));

        database.getProducts().add(new Product(001L, "jordan", "black", 5000, 25, ProductCategory.SNEAKER,
                "https://www.swooshstore.com/images/Nike-Dunk-SB-Low-Heels-w.jpg"));
        database.getProducts().add(new Product(002L, "nike", "blue", 5000, 25, ProductCategory.SNEAKER,
                "https://sun9-70.userapi.com/impg/DQBGU5C4MQpbx1Qt9acGsw1RTi7EaNO4kHd0MA/kaLhgbyOgxU.jpg?size=510x510&quality=96&sign=d2cb33e85050c5eb5a931189cf08c798&type=album"));
        database.getProducts().add(new Product(003L, "boots", "yellow", 5000, 25, ProductCategory.BOOT,
                "https://cdn1.ozone.ru/s3/multimedia-8/6380186684.jpg"));
        database.getProducts().add(new Product(004L, "stileto", "pink", 5000, 25, ProductCategory.STILETTO,
                "https://i.pinimg.com/originals/4b/c2/ab/4bc2ab9d886a6e2895b30cd8b4b2e77c.jpg"));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is undeployed or destroyed");
    }

}
