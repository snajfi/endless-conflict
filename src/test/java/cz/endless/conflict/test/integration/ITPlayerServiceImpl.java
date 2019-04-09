package cz.endless.conflict.test.integration;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertTrue;

/**
 * Created by snajfi1 on 09.04.2019.
 */
@Testcontainers
class ITPlayerServiceImpl {

    @Container
    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer()
            .withDatabaseName("ec")
            .withPassword("ec")
            .withPassword("ec");

    @Test
    void dbContainer() {
        assertTrue(POSTGRE_SQL_CONTAINER.isRunning());
    }


}
