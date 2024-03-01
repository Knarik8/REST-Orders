package org.example.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private String dbUrl;
    private String username;
    private String password;
    private String dbDriverName;

    public static Connection connection;
    private volatile static ConnectionManager instance;

    private ConnectionManager() {
        try {
            loadProperties();
            Class.forName(dbDriverName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream("/db.properties")){
            properties.load(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }

        dbUrl = properties.getProperty("db.source.url");
        username = properties.getProperty("db.source.username");
        password = properties.getProperty("db.source.password");
        dbDriverName = properties.getProperty("db.source.driverClass");

    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) {
                    instance = new ConnectionManager();
                }
            }
        }
        return instance;

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
