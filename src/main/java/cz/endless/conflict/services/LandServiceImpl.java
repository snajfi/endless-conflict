package cz.endless.conflict.services;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.age.Age;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by dobeji1 on 12.04.2019.
 */
@Stateless
public class LandServiceImpl implements LandService {

    @PersistenceContext(unitName = "endless_conflict")
    private EntityManager entityManager;

    @Override
    public Land createLand(Land land) {
        return entityManager.merge(land);
    }

    @Override
    public Land findLandByPlayerAndAge(Player player, Age age) {
        TypedQuery<Land> query = entityManager.createNamedQuery(Land.GET_LAND_BY_PLAYER_AND_AGE,Land.class);
        query.setParameter("player", player);
        query.setParameter("age", age);
        List<Land> lands = query.getResultList();
        if (!lands.isEmpty()) {
            return lands.get(0);
        }
        return null;
    }

    @Override
    public boolean isLandNameAvailableInAge(String name, Age age) {
        TypedQuery<Land> query = entityManager.createNamedQuery(Land.GET_LAND_BY_NAME_AND_AGE,Land.class);
        query.setParameter("name", name);
        query.setParameter("age", age);
        return query.getResultList().isEmpty();
    }



    @Override
    public int getLastLandInAgeId(Age age) {
        TypedQuery<Integer> query = entityManager.createNamedQuery(Land.GET_LAST_LAND_IN_AGE_ID,Integer.class);
        query.setParameter("age", age);
        Integer landId = query.getSingleResult();
        if (landId == null) {
            return 10;
        }
        return landId + 1;
    }
}
