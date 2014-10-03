package repository.impl;

import models.UserEntity;
import org.apache.log4j.Logger;
import repository.ifc.LoginRepository;

import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * Created by ivald79 on 10/06/2014.
 */
public class LoginRepositoryImpl extends BaseRepositoryImpl<UserEntity> implements LoginRepository {

    private static final Logger LOGGER = Logger.getLogger(LoginRepositoryImpl.class.getName());

    public LoginRepositoryImpl() {
        super(UserEntity.class);
    }

    @Transactional
    public UserEntity findEntityByUserNameAndPass(String username) throws Exception {
        UserEntity user = null;

        try {
            Query query = em.createNamedQuery(UserEntity.FIND_BY_USERNAME)
                    .setParameter("p1", username);
            user = (UserEntity) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new Exception("Login failed. User " + username + " was not found.");
        }

        return user;
    }
}
