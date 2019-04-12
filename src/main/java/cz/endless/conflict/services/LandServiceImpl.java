package cz.endless.conflict.services;

import cz.endless.conflict.entities.Land;
import cz.endless.conflict.entities.Player;
import cz.endless.conflict.entities.age.Age;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by dobeji1 on 12.04.2019.
 */
@Stateless
public class LandServiceImpl implements LandService {

    @PersistenceContext(unitName = "endless_conflict")
    private EntityManager entityManager;

    @Override
    public void createLand(Land land) {
        Player player = land.getPlayer();
        player.getLands().add(land);
        entityManager.merge(player);
    }

    @Override
    public boolean isLandNameAvailableInAge(String name, Age age) {
        TypedQuery<Land> query = entityManager.createNamedQuery(Land.GET_LAND_BY_NAME_AND_AGE,Land.class);
        query.setParameter("name", name);
        query.setParameter("age", age);
        return query.getResultList().isEmpty();
    }
}
