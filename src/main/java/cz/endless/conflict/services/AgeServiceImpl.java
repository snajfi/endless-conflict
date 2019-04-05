package cz.endless.conflict.services;

import cz.endless.conflict.entities.age.Age;
import cz.endless.conflict.entities.age.AgeConfiguration;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by dobeji1 on 15.03.2019.
 */
@Stateless
public class AgeServiceImpl implements AgeService {

    @PersistenceContext(unitName = "endless_conflict")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Age createNewAge(Age age) {
        age.setCreationDate(new Date());
        return entityManager.merge(age);
    }

    @Override
    public Age getAgeByNumber(int number) {
        TypedQuery<Age> query = entityManager.createNamedQuery(Age.GET_AGE_BY_NUMBER,Age.class);
        query.setParameter("number", number);
        List<Age> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);

        }
        return null;
    }

    @Override
    public List<Age> getAllAges() {
        return entityManager.createNamedQuery(Age.GET_ALL_AGES,Age.class).getResultList();
    }

    @Override
    @Transactional
    public AgeConfiguration createNewAgeConfiguration(AgeConfiguration ageConfiguration) {
        return entityManager.merge(ageConfiguration);
    }

    @Override
    public AgeConfiguration getAgeConfigurationById(Long id) {
        TypedQuery<AgeConfiguration> query = entityManager.createNamedQuery(AgeConfiguration.GET_AGE_CONFIGURATION_BY_ID,AgeConfiguration.class);
        query.setParameter("id",id);
        List<AgeConfiguration> ageConfigurations = query.getResultList();
        if (!ageConfigurations.isEmpty()) {
            return ageConfigurations.get(0);

        }
        return null;
    }

    @Override
    public List<AgeConfiguration> getAllAgesConfigurations() {
        return entityManager.createNamedQuery(AgeConfiguration.GET_ALL_AGE_CONFIGURATIONS,AgeConfiguration.class).getResultList();
    }

    @Override
    public int getNextAgeNumber() {
        TypedQuery<Integer> query = entityManager.createNamedQuery(Age.GET_LAST_AGE_NUMBER,Integer.class);
        List<Integer> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0) + 1;
        }
        else {
            return 1;
        }
    }
}
