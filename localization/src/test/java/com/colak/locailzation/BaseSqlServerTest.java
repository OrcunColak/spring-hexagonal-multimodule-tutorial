package com.colak.locailzation;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

// This annotation will start and stop the container automatically
@Testcontainers
public abstract class BaseSqlServerTest {

    @SuppressWarnings("resource")
    @Container
    @ServiceConnection
    private static final MSSQLServerContainer<?> MSSQL_SERVER_CONTAINER = new MSSQLServerContainer<>(
            DockerImageName.parse("mcr.microsoft.com/mssql/server:2022-latest"))
            .acceptLicense();

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

    }

}
