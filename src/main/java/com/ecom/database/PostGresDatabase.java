package com.ecom.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.ecom.app.model.entity.ItemCart;
import com.ecom.app.model.entity.Order;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.app.model.view.html.DbTableAnnotation;
import com.ecom.app.model.view.html.DbTableColAnnotation;
import com.ecom.app.model.view.html.DbTableId;

import java.math.BigDecimal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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

    public static void updateSchema() {
        System.out.println("*************** updating schema database *************");

        try {
            Connection connection = PostGresDatabase.getInstance().getConnection();

            List<Class<?>> entities = new ArrayList<>();
            entities.add(User.class);
            entities.add(Product.class);
            entities.add(ItemCart.class);
             entities.add(Order.class);

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

                    if (field.isAnnotationPresent(DbTableId.class)) {
                        sqlBuilder.append(dbTableColumn.name()).append(" SERIAL PRIMARY KEY,");
                    } else {
                        sqlBuilder.append(dbTableColumn.name()).append(" ")
                                .append(dbTableColumn.definition()).append(",");
                    }
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
                if (!field.isAnnotationPresent(DbTableColAnnotation.class)
                        || field.isAnnotationPresent(DbTableId.class))
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
                } else if (param instanceof Double) {
                    sqlStmt.setDouble(paramIdx++, (double) param);
                }

                else
                    sqlStmt.setString(paramIdx++, param.toString());
            }

            sqlStmt.executeUpdate();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> select(Class<T> filter) {
        try {
            Class<?> clazz = filter;
            System.out.println();
            System.out.println("Clazz>>>>>>>>>>" + clazz.getName());

            if (!clazz.isAnnotationPresent(DbTableAnnotation.class))
                return new ArrayList<>();

            DbTableAnnotation dbTable = clazz.getAnnotation(DbTableAnnotation.class);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ")
                    .append(dbTable.name()).append(";");
            Connection conn = PostGresDatabase.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> result = new ArrayList<>();

            while (resultSet.next()) {
                T object = (T) clazz.getDeclaredConstructor().newInstance();
                 List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

                for (Field field : fields) {
                    field.setAccessible(true);
                    DbTableColAnnotation dbColumn = field.getAnnotation(DbTableColAnnotation.class);
                    if (dbColumn != null) {
                        String columnName = dbColumn.name();

                        if (field.getType().isEnum()) {
                            String enumValue = resultSet.getString(columnName);
                            if (enumValue != null) {
                                Object enumConstant = Enum.valueOf((Class<Enum>) field.getType(), enumValue);
                                field.set(object, enumConstant);
                            }
                        } else if (field.getType() == double.class) {
                            double value = resultSet.getDouble(columnName);
                            field.set(object, value);
                        } else if (field.getType() == int.class) {
                            int value = resultSet.getInt(columnName);
                            if (field.getType() == Long.class) {
                                field.set(object, (long) value);
                            } else {
                                field.set(object, value);
                            }
                        } else if (field.getType() == Long.class) {
                            long value = resultSet.getLong(columnName);
                            field.set(object, value);
                        } else {
                            Object value = resultSet.getObject(columnName);
                            field.set(object, value);
                        }
                    }
                }

                result.add(object);
                
            }

            return result;

        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException
                | NoSuchMethodException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource is null. Initialization error.");
        }

        return dataSource.getConnection();
    }
    public static void deleteProduct(Class<?> clazz, Long id) {
        try {
            if (!clazz.isAnnotationPresent(DbTableAnnotation.class))
                return;
    
            DbTableAnnotation dbTable = clazz.getAnnotation(DbTableAnnotation.class);
    
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("DELETE FROM ").append(dbTable.name()).append(" WHERE id = ?");
    
            String query = queryBuilder.toString();
            System.out.println("Delete Query: " + query);
    
            Connection connection = PostGresDatabase.getInstance().getConnection();
            PreparedStatement sqlStmt = connection.prepareStatement(query);
            sqlStmt.setLong(1, id);
    
            sqlStmt.executeUpdate();
    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
