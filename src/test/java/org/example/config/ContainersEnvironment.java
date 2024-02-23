package org.example.config;

import org.example.containers.MySQLTestContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainersEnvironment  {

    @Container
    public static MySQLContainer mySQLContainer = MySQLTestContainer.getInstance()
            .withInitScript("schema.sql");
}
