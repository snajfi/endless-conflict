package cz.endless.conflict.services;

import cz.endless.conflict.entities.Age;
import cz.endless.conflict.entities.AgeConfiguration;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by dobeji1 on 15.03.2019.
 */
@ApplicationScoped
public class AgeServiceImpl implements AgeService {

    @PersistenceContext(unitName = "endless_conflict")
    private EntityManager entityManager;

    @Inject
    private Logger logger;


    @Override
    @Transactional
    public Age createNewAge(Age age) {
        age.setCreationDate(new Date());
        return entityManager.merge(age);
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
}
