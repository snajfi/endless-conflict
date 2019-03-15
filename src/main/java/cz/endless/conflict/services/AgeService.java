package cz.endless.conflict.services;

import cz.endless.conflict.entities.Age;
import cz.endless.conflict.entities.AgeConfiguration;

import java.util.List;

/**
 * Created by dobeji1 on 15.03.2019.
 */

public interface AgeService {

    Age createNewAge(Age age);

    List<Age> getAllAges();

    AgeConfiguration createNewAgeConfiguration(AgeConfiguration ageConfiguration);

    AgeConfiguration getAgeConfigurationById(Long id);

    List<AgeConfiguration> getAllAgesConfigurations();

}
