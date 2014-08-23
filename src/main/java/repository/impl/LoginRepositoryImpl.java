package repository.impl;

import models.UserEntity;
import repository.ifc.LoginRepository;

import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * Created by ivald79 on 10/06/2014.
 */
public class LoginRepositoryImpl extends BaseRepositoryImpl<UserEntity> implements LoginRepository {

    public LoginRepositoryImpl() {
        super(UserEntity.class);
    }

    @Transactional
    public UserEntity findEntityByUserNameAndPass(String username) {
        Query query = em.createNamedQuery(UserEntity.FIND_BY_USERNAME_PASSWORD)
                .setParameter("p1", username);
        return (UserEntity) query.getSingleResult();
    }
}
