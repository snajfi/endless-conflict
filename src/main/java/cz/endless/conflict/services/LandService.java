package cz.endless.conflict.services;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.age.Age;

/**
 * Created by dobeji1 on 12.04.2019.
 */

public interface LandService {

    Land createLand(Land land);

    Land findLandByPlayerAndAge(Player player, Age age);

    boolean isLandNameAvailableInAge(String name, Age age);

    int getLastLandInAgeId(Age age);
}
