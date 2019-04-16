package cz.endless.conflict.test.integration;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.services.*;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dobeji1 on 15.04.2019.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class ITLandServiceImpl {

    @Container
    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer()
            .withDatabaseName("ec")
            .withUsername("ec")
            .withPassword("ec");

    private static EntityManager entityManager;

    private static LandService landService;

    private static Age testingAge;
    private static Age testingAge2;
    private static Land testingLand;
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

        landService = new LandServiceImpl();

        Field field = landService.getClass().getDeclaredField("entityManager");
        field.setAccessible(true);
        field.set(landService,entityManager);

        AgeService ageService = new AgeServiceImpl();

        Field field2 = ageService.getClass().getDeclaredField("entityManager");
        field2.setAccessible(true);
        field2.set(ageService,entityManager);

        PlayerService playerService = new PlayerServiceImpl();

        Field field3 = playerService.getClass().getDeclaredField("entityManager");
        field3.setAccessible(true);
        field3.set(playerService,entityManager);


        // Age for testing

        testingAge = new Age();
        testingAge.setStartedAt(new Date());
        testingAge.setNumber(1);

        testingAge2 = new Age();
        testingAge2.setStartedAt(new Date());
        testingAge2.setNumber(2);

        testingLand = new Land();
        testingLand.setName("name");
        testingLand.setLandInAgeId(10);

        testingPlayer = new Player();
        testingPlayer.setLands(new ArrayList<>());
        testingPlayer.setEmail("email@email.com");
        testingPlayer.setLogin("Login");
        testingPlayer.setNickname("Nickname");
        testingPlayer.setPassword("Password");

        entityManager.getTransaction().begin();
        testingPlayer = playerService.saveNewPlayer(testingPlayer);
        testingAge = ageService.createNewAge(testingAge);
        testingAge2 = ageService.createNewAge(testingAge2);
        entityManager.getTransaction().commit();

        testingLand.setPlayer(testingPlayer);
    }

    @Test
    @Order(1)
    void createLandTest() {
        testingLand.setAge(testingAge);
        Land land = landService.createLand(testingLand);
        assertTrue(land.getId()!= null);
    }

    @Test
    @Order(2)
    void findLandByPlayerAndAgeTest1() {
        assertNotNull(landService.findLandByPlayerAndAge(testingPlayer, testingAge));
    }

    @Test
    @Order(3)
    void findLandByPlayerAndAgeTest2() {
        assertNull(landService.findLandByPlayerAndAge(null, null));
    }


    @Test
    @Order(4)
    void isLandNameAvailableInAgeTest() {
        assertFalse(landService.isLandNameAvailableInAge(testingLand.getName(), testingAge));
    }

    @Test
    @Order(5)
    void getNextAgeNumberTest() {
        assertTrue(landService.getLastLandInAgeId(testingAge)==11);
    }

    @Test
    @Order(6)
    void getNextAgeNumberTest2() {
        assertTrue(landService.getLastLandInAgeId(testingAge2)==10);
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
