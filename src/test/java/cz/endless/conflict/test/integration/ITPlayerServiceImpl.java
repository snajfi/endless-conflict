package cz.endless.conflict.test.integration;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.services.PlayerService;
import cz.endless.conflict.services.PlayerServiceImpl;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Created by snajfi1 on 09.04.2019.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class ITPlayerServiceImpl {

    @Container
    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer()
            .withDatabaseName("ec")
            .withUsername("ec")
            .withPassword("ec");

    private static EntityManager entityManager;

    private static PlayerService playerService;

    private static Player testingPlayer;



    @BeforeAll
    static void init() throws NoSuchFieldException, IllegalAccessException {

        Map<String,String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.url",POSTGRE_SQL_CONTAINER.getJdbcUrl());
        properties.put("javax.persistence.jdbc.user",POSTGRE_SQL_CONTAINER.getUsername());
        properties.put("javax.persistence.jdbc.password",POSTGRE_SQL_CONTAINER.getPassword());
        properties.put("javax.persistence.jdbc.driver",POSTGRE_SQL_CONTAINER.getDriverClassName());

        properties.put("javax.persistence.schema-generation.database.action","create");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("integrationTesting",properties);

        entityManager = emf.createEntityManager();

        playerService = new PlayerServiceImpl();

        Field field = playerService.getClass().getDeclaredField("entityManager");
        field.setAccessible(true);
        field.set(playerService,entityManager );


        // Player for testing
        testingPlayer = new Player();
        testingPlayer.setLogin("loginName");
        testingPlayer.setEmail("something@somewhere.com");
        testingPlayer.setNickname("Nickname");
        testingPlayer.setPassword("123456");
    }

    @Test
    @Order(1)
    void isEmailAvailableTest1() {
        // player is not in DB yet so email is free
        assertTrue(playerService.isEmailAvailable(testingPlayer.getEmail()));
    }

    @Test
    @Order(2)
    void insertingPlayer() {
        // save player
        Player player = playerService.saveNewPlayer(testingPlayer);
        assertTrue(player.getId()!=null);
    }

    @Test
    @Order(3)
    void searchForPlayer() {
        assertNotNull(playerService.findPlayerByLogin(testingPlayer.getLogin()));
    }

    @Test
    @Order(4)
    void isEmailAvailableTest2() {
        assertFalse(playerService.isEmailAvailable(testingPlayer.getEmail()));
    }

    @BeforeEach
    private void startTransaction() {
        entityManager.getTransaction().begin();
    }

    @AfterEach
    private void commitTransaction() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
    }

}
