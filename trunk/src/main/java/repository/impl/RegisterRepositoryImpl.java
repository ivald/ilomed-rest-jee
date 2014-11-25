package repository.impl;

import models.UserEntity;
import org.apache.log4j.Logger;
import repository.ifc.RegisterRepository;
import services.ifc.LoginService;
import services.impl.SequenceGeneratorServiceImpl;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by ivald79 on 24/09/2014.
 */
public class RegisterRepositoryImpl extends BaseRepositoryImpl<UserEntity> implements RegisterRepository {

    private static final Logger LOGGER = Logger.getLogger(RegisterRepositoryImpl.class.getName());

    @Inject
    private LoginService loginService;

    public RegisterRepositoryImpl() {
        super(UserEntity.class);
    }

    @Override
    public boolean isUserNameValid(String username) {
        try {
            loginService.findEntityByUserNameAndPass(username);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
