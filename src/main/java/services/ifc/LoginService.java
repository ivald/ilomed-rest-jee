package services.ifc;

import models.UserEntity;

/**
 * Created by ivald79 on 16/06/2014.
 */
public interface LoginService {
    /**
     * The method returns a user by their user name and password.
     *
     * @param username
     * @return UserEntity
     */
    public UserEntity findEntityByUserNameAndPass(String username);
}
