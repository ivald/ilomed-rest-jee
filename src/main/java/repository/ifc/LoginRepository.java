package repository.ifc;

import models.UserEntity;

/**
 * Created by ivald79 on 10/06/2014.
 */
public interface LoginRepository {
    /**
     * The method returns a user by their user name and password.
     *
     * @param username
     * @return UserEntity
     */
    UserEntity findEntityByUserNameAndPass(String username) throws Exception;
}
