package cz.endless.conflict.services;

import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.entities.age.AgeConfiguration;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dobeji1 on 12.04.2019.
 */

public class ImportServiceImpl implements ImportService {

    @Inject private PlayerService playerService;
    @Inject private AgeService ageService;


    @Override
    public void importData() {
        importUsers();
        importAges();
    }

    private void importUsers() {
        Player player1 = new Player();
        player1.setEmail("player1@email.com");
        player1.setLogin("Player1");
        player1.setNickname("Player1");
        player1.setPassword("123456");
        playerService.saveNewPlayer(player1);

        Player player2 = new Player();
        player2.setEmail("player2@email.com");
        player2.setLogin("Player2");
        player2.setNickname("Player2");
        player2.setPassword("123456");
        playerService.saveNewPlayer(player2);
    }

    private void importAges() {
        AgeConfiguration ageConfiguration = new AgeConfiguration();
        ageConfiguration.setName("Classic");
        ageConfiguration.setLockedStack(200);
        ageConfiguration.setRoundStack(140);

        Age age1 = new Age();
        age1.setNumber(1);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        age1.setEndedAt(cal.getTime());
        age1.setCreator(playerService.getAllPlayers().get(0));
        age1.setDescription("Age 1");
        age1.setAgeConfiguration(ageConfiguration);
        age1.setStartedAt(new Date());
        ageService.createNewAge(age1);

        Age age2 = new Age();
        age2.setNumber(2);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        age2.setEndedAt(cal.getTime());
        age2.setCreator(playerService.getAllPlayers().get(0));
        age2.setDescription("Age 2");
        age2.setAgeConfiguration(ageConfiguration);
        age2.setStartedAt(new Date());
        ageService.createNewAge(age2);
    }


}
