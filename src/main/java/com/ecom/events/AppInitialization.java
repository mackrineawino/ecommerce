package com.ecom.events;

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import com.ecom.database.PostGresDatabase;

@WebListener
public class AppInitialization implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("*************** Initializing database *************");

        // database.getCartItems().add(new ItemCart( 1L,"nike", ProductCategory.BOOT,
        // 333));
        // database.getCartItems().add(new ItemCart(2L, "nike", ProductCategory.BOOT,
        // 333));

        // database.getData().add(new User(01L, "hillary@test.com", "1234",
        // UserType.NORMAL_USER));
        // database.getData().add(new User(02L, "sedah@test.com", "123",
        // UserType.NORMAL_USER));
        // database.getData().add(new User(03L, "chloe@test.com", "12345",
        // UserType.ADMIN));

        // database.getData().add(new Product(1L, "jordan", "black", 1500, 25,
        // ProductCategory.SNEAKER,
        // "https://github.com/mackrineawino/images/blob/main/805sa-pink-40-saheb-pink-original-imafghzfctaf4xdy-removebg-preview.png?raw=true"));
        // database.getData().add(new Product(2L, "nike", "blue", 3000, 25,
        // ProductCategory.SNEAKER,
        // "https://github.com/mackrineawino/images/blob/main/kaLhgbyOgxU-removebg-preview.png?raw=true"));
        // database.getData().add(new Product(3L, "boots", "yellow", 1500, 25,
        // ProductCategory.BOOT,
        // "https://github.com/mackrineawino/images/blob/main/6380186684-removebg-preview.png?raw=true"));
        // database.getData().add(new Product(5L, "stileto", "pink", 2000, 25,
        // ProductCategory.STILETTO,
        // "https://github.com/mackrineawino/images/blob/main/4bc2ab9d886a6e2895b30cd8b4b2e77c-removebg-preview.png?raw=true"));
        // database.getData().add(new Product(6L, "Doll", "pink", 2500, 25,
        // ProductCategory.DOLL_SHOE,
        // "https://github.com/mackrineawino/images/blob/main/1aa_f30b5dc1-d535-4403-bc1d-af9693c8e48d-removebg-preview.png?raw=true"));

        // database.getData().add(new Product(004L, "Boots", "pink", 5000, 25,
        // ProductCategory.BOOT,
        // "https://github.com/mackrineawino/images/blob/main/Nike-Dunk-SB-Low-Heels-w-removebg-preview%20(2).png?raw=true"));
        // try {
        //     Connection connection = PostGresDatabase.getInstance().getConnection();

        //     System.out.println("Database connection established successfully.");
        //     List<Class<?>> entities = new ArrayList<>();
        //     entities.add(User.class);
        //     entities.add(Product.class);
        //     entities.add(ItemCart.class);

        //     for (Class<?> clazz : entities) {
        //         if (!clazz.isAnnotationPresent(DbTableAnnotation.class))
        //             continue;

        //   DbTableAnnotation dbTable = clazz.getAnnotation(DbTableAnnotation.class);

        //         StringBuilder sqlBuilder = new StringBuilder();

        //         sqlBuilder.append("create table if not exists ").append(dbTable.name()).append("(");
        //         for (Field field : clazz.getDeclaredFields()) {
        //             if (!field.isAnnotationPresent(DbTableColAnnotation.class))
        //                 continue;

        //             DbTableColAnnotation dbTableColumn = field.getAnnotation(DbTableColAnnotation.class);

        //             sqlBuilder.append(dbTableColumn.name()).append(" ").append(dbTableColumn.definition()).append(",");
        //         }

        //         sqlBuilder.append(")");

        //         connection.prepareStatement(sqlBuilder.toString().replace(",)", ")")).executeUpdate();

        //     }

        // } catch (SQLException ex) {
        //     System.out.println(ex);
        // }
        PostGresDatabase.updateSchema();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PostGresDatabase database = PostGresDatabase.getInstance();

            if (database.getConnection() != null) {
                database.getConnection().close();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
