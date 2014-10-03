package services.ifc;

import models.UserEntity;
import repository.ifc.BaseIfc;

/**
 * Created by ivald79 on 24/09/2014.
 */
public interface RegisterService {

    void save(UserEntity entity) throws Exception ;

    boolean isUserNameValid(String username);
}
