package services.ifc;

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
     * @throws exceptions.WebResponseException
     */
    public UserEntity findEntityByUserNameAndPass(String username) throws Exception;
}
