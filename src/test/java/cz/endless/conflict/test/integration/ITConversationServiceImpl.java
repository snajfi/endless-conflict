package cz.endless.conflict.test.integration;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.communication.Conversation;
import cz.endless.conflict.services.ConversationService;
import cz.endless.conflict.services.ConversationServiceImpl;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dobeji1 on 15.04.2019.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class ITConversationServiceImpl {

    @Container
    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer()
            .withDatabaseName("ec")
            .withUsername("ec")
            .withPassword("ec");

    private static EntityManager entityManager;

    private static ConversationService conversationService;

    private static Player testingPlayer;
    private static Conversation testingConversation;

    private static String LAST_MESSAGE="lastMessage";

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

        conversationService = new ConversationServiceImpl();

        Field field = conversationService.getClass().getDeclaredField("entityManager");
        field.setAccessible(true);
        field.set(conversationService,entityManager );

        PlayerService playerService = new PlayerServiceImpl();

        Field field2 = playerService.getClass().getDeclaredField("entityManager");
        field2.setAccessible(true);
        field2.set(playerService,entityManager);

        // Data for testing
        testingPlayer = new Player();
        testingPlayer.setLands(new ArrayList<>());
        testingPlayer.setEmail("email@email.com");
        testingPlayer.setLogin("Login");
        testingPlayer.setNickname("Nickname");
        testingPlayer.setPassword("Password");

        entityManager.getTransaction().begin();
        testingPlayer = playerService.saveNewPlayer(testingPlayer);
        entityManager.getTransaction().commit();

        testingConversation = new Conversation();
        testingConversation.setId(1L);


    }

    @Test
    @Order(1)
    void createConversationTest() {
        testingConversation.setRecipients(Arrays.asList(testingPlayer));
        Conversation conversation = conversationService.createConversation(testingConversation, "message", testingPlayer);
        assertTrue(conversation.getId()!=null);
    }

    @Test
    @Order(2)
    void sendMessageTest() {
        Conversation conversation = conversationService.getConversationById(testingConversation.getId());
        conversation = conversationService.sendMessage(conversation, LAST_MESSAGE, testingPlayer);
        assertTrue(conversation.getMessages().size()==2);
    }


    @Test
    @Order(3)
    void getConversationByIdTest() {
        assertNotNull(conversationService.getConversationById(testingConversation.getId()));
    }

    @Test
    @Order(4)
    void getConversationByIdTest2() {
        assertNull(conversationService.getConversationById(2L));
    }

    @Test
    @Order(5)
    void getMessagesForConversationAndOwnerTest() {
        assertTrue(conversationService.getMessagesForConversationAndOwner(testingConversation, testingPlayer).size()==2);
    }

    @Test
    @Order(6)
    void getLastMessageForConversationAndOwnerTest() {
        assertEquals(LAST_MESSAGE, conversationService.getLastMessageForConversationAndOwner(testingConversation, testingPlayer).getText());
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
