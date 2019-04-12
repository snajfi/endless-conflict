package cz.endless.conflict.services;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.age.Age;

/**
 * Created by dobeji1 on 12.04.2019.
 */

public interface LandService {

    void createLand(Land land);

    boolean isLandNameAvailableInAge(String name, Age age);
}
