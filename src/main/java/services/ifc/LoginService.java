package services.ifc;

import exceptions.LoginException;
import models.UserEntity;

/**
 * Created by ivald79 on 16/06/2014.
 */
public interface LoginService {
    /**
     * The method returns a user by their user name
     *
     * @param username
     * @return
     * @throws LoginException
     */
    public UserEntity findEntityByUserNameAndPass(String username) throws Exception;
}
