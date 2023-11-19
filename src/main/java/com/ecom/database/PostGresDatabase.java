package com.ecom.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostGresDatabase implements Serializable {

    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce";

    private static final String USER = "test1";

    private static final String PASSWORD = "test1";

    private static PostGresDatabase database;

    private Connection connection;

    private PostGresDatabase() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static PostGresDatabase getInstance() throws SQLException{
        if (database == null)
            database = new PostGresDatabase();

        return database;

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
