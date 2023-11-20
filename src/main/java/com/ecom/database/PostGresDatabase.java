package com.ecom.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.app.model.view.html.DbTableAnnotation;
import com.ecom.app.model.view.html.DbTableColAnnotation;
import com.ecom.app.model.view.html.DbTableId;

import java.math.BigDecimal;

import java.lang.reflect.Field;

public class PostGresDatabase implements Serializable {

    private static PostGresDatabase database;

    private DataSource dataSource;

    private PostGresDatabase() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:jboss/datasources/ExampleDS");
        } catch (NamingException e) {
            throw new RuntimeException("Error looking up DataSource in JNDI", e);
        }
    }

    public static PostGresDatabase getInstance() {
        if (database == null) {
            database = new PostGresDatabase();
        }
        return database;
    }
        public static void updateSchema(){
        System.out.println("*************** updating schema database *************");

        try {
            Connection connection = PostGresDatabase.getInstance().getConnection();

            List<Class<?>> entities = new ArrayList<>();
          entities.add(User.class);
            entities.add(Product.class);
            entities.add(ItemCart.class);

            for (Class<?> clazz : entities) {
                if (!clazz.isAnnotationPresent(DbTableAnnotation.class))
                    continue;

                DbTableAnnotation dbTable = clazz.getAnnotation(DbTableAnnotation.class);

                StringBuilder sqlBuilder = new StringBuilder();

                sqlBuilder.append("create table if not exists ").append(dbTable.name()).append("(");

                List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

                for (Field field : fields) {
                    if (!field.isAnnotationPresent(DbTableColAnnotation.class))
                        continue;

                    DbTableColAnnotation dbTableColumn = field.getAnnotation(DbTableColAnnotation.class);

                    sqlBuilder.append(dbTableColumn.name()).append(" ")
                        .append(dbTableColumn.definition())
                        .append(field.isAnnotationPresent(DbTableId.class)?" NOT NULL AUTO_INCREMENT PRIMARY KEY" : "")
                        .append(",");
                }

                sqlBuilder.append(")");

                String tableCreationSql = sqlBuilder.toString().replace(",)", ")");
                System.out.println("Creating table: " + tableCreationSql);
                connection.prepareStatement(tableCreationSql).executeUpdate();

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }


    }

    public static void insert(Object entity) {

        try {

            Class<?> clazz = entity.getClass();
            if (!clazz.isAnnotationPresent(DbTableAnnotation.class))
                return;

            DbTableAnnotation dbTable = clazz.getAnnotation(DbTableAnnotation.class);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder columnBuilder = new StringBuilder();
            StringBuilder paramPlaceHolderBuilder = new StringBuilder();
            List<Object> parameters = new ArrayList<>();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbTableColAnnotation.class) || field.isAnnotationPresent(DbTableId.class))
                    continue;

                field.setAccessible(true);
                if (field.get(entity) == null)
                    continue;

                DbTableColAnnotation dbTableColumn = field.getAnnotation(DbTableColAnnotation.class);

                columnBuilder.append(dbTableColumn.name()).append(",");
                paramPlaceHolderBuilder.append("?").append(",");
                parameters.add(field.get(entity));

            }

            String queryBuilder = "insert into " +
                dbTable.name() +
                "(" +
                columnBuilder +
                ")" +
                " values(" +
                paramPlaceHolderBuilder +
                ")";

            String query = queryBuilder.replace(",)", ")");
            System.out.println("Query: " + query);

            PreparedStatement sqlStmt = PostGresDatabase.getInstance().getConnection()
                .prepareStatement(query);

            int paramIdx = 1;
            for (Object param : parameters) {
                if (param instanceof BigDecimal)
                    sqlStmt.setBigDecimal(paramIdx++, (BigDecimal) param);
                else if (param instanceof Long)
                    sqlStmt.setLong(paramIdx++, (long) param);
                else if (param instanceof UserType) {
                    sqlStmt.setString(paramIdx++, ((UserType) param).name());
                } else
                    sqlStmt.setString(paramIdx++, param.toString());
            }
            

            sqlStmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource is null. Initialization error.");
        }
        
        return dataSource.getConnection();
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
