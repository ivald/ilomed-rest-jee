package repository.impl;

import models.UserEntity;
import org.apache.log4j.Logger;
import repository.ifc.RegisterRepository;
import services.impl.SequenceGeneratorServiceImpl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by ivald79 on 24/09/2014.
 */
public class RegisterRepositoryImpl extends BaseRepositoryImpl<UserEntity> implements RegisterRepository {

    private static final Logger LOGGER = Logger.getLogger(RegisterRepositoryImpl.class.getName());

    public RegisterRepositoryImpl() {
        super(UserEntity.class);
    }

    @Override
    public boolean isUserNameValid(String username) {
        try {
            Query query = em.createNamedQuery(UserEntity.FIND_BY_USERNAME)
                    .setParameter("p1", username);
            query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage());
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
