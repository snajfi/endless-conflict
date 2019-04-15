package cz.endless.conflict.test.integration;

import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.entities.age.AgeConfiguration;
import cz.endless.conflict.entities.age.WinCondition;
import cz.endless.conflict.entities.age.WinConditions;
import cz.endless.conflict.services.AgeService;
import cz.endless.conflict.services.AgeServiceImpl;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dobeji1 on 15.04.2019.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class ITAgeServiceImpl {

    @Container
    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer()
            .withDatabaseName("ec")
            .withUsername("ec")
            .withPassword("ec");

    private static EntityManager entityManager;

    private static AgeService ageService;

    private static Age testingAge;
    private static WinConditions winConditions;
    private static AgeConfiguration ageConfiguration;


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

        ageService = new AgeServiceImpl();

        Field field = ageService.getClass().getDeclaredField("entityManager");
        field.setAccessible(true);
        field.set(ageService,entityManager );

        // Age for testing
        winConditions = new WinConditions();
        winConditions.setAllRequired(true);
        winConditions.setWinCondition(Arrays.asList(new WinCondition()));

        ageConfiguration = new AgeConfiguration();
        ageConfiguration.setId(1L);

        testingAge = new Age();
        testingAge.setStartedAt(new Date());
        testingAge.setWinConditions(winConditions);
        testingAge.setNumber(1);
        testingAge.setAgeConfiguration(ageConfiguration);
    }

    @Test
    @Order(1)
    void createNewAgeTest() {
        Age age = ageService.createNewAge(testingAge);
        assertTrue(age.getId()!=null);
    }

    @Test
    @Order(2)
    void getAgeByNumberTest1() {
        assertNotNull(ageService.getAgeByNumber(testingAge.getNumber()));
    }

    @Test
    @Order(3)
    void getAgeByNumberTest2() {
        assertNull(ageService.getAgeByNumber(2));
    }

    @Test
    @Order(4)
    void getAllAgesTest() {
        assertTrue(ageService.getAllAges().size()==1);
    }

    @Test
    @Order(5)
    void getAgeConfigurationByIdTest() {
        assertNotNull(ageService.getAgeConfigurationById(ageConfiguration.getId()));
    }

    @Test
    @Order(6)
    void getAgeConfigurationByIdTest2() {
        assertNull(ageService.getAgeConfigurationById(2L));
    }

    @Test
    @Order(7)
    void getAllAgesConfigurationsTest() {
        assertTrue(ageService.getAllAgesConfigurations().size()==1);
    }
    @Test
    @Order(8)
    void getNextAgeNumberTest() {
        assertTrue(ageService.getNextAgeNumber()==2);
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
