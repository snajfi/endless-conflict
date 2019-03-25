package cz.endless.conflict.services;

import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.entities.age.AgeConfiguration;

import java.util.List;

/**
 * Created by dobeji1 on 15.03.2019.
 */

public interface AgeService {

    Age createNewAge(Age age);

    Age getAgeByNumber(int number);

    List<Age> getAllAges();

    AgeConfiguration createNewAgeConfiguration(AgeConfiguration ageConfiguration);

    AgeConfiguration getAgeConfigurationById(Long id);

    List<AgeConfiguration> getAllAgesConfigurations();

    int getNextAgeNumber();

}
