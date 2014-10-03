package repository.ifc;

import models.UserEntity;

/**
 * Created by ivald79 on 24/09/2014.
 */
public interface RegisterRepository extends BaseIfc<UserEntity> {

    boolean isUserNameValid(String username);
}
