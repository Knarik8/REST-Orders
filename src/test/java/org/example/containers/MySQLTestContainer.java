package org.example.containers;

import org.testcontainers.containers.MySQLContainer;

public class MySQLTestContainer extends MySQLContainer<MySQLTestContainer> {

    public static final String IMAGE_VERSION = "mysql:8.3.0";
    public static final String DATABASE_NAME = "orders";
    public static MySQLTestContainer container;

    public MySQLTestContainer(){
        super(IMAGE_VERSION);
    }

    public static MySQLTestContainer getInstance(){
        if (container ==null){
            container = new MySQLTestContainer().withDatabaseName(DATABASE_NAME);
        }
        return container;
    }


    @Override
    public void start() {
        super.start();
        System.setProperty("DB_UR", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());

    }

    @Override
    public void stop() {
    }
}
