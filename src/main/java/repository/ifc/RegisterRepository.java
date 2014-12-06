package repository.ifc;

import models.UserEntity;
import models.WebResponse;

/**
 * Created by ivald79 on 24/09/2014.
 */
public interface RegisterRepository extends BaseIfc<UserEntity> {

    boolean isUserNameValid(String username);

    public WebResponse registration(String username,String password,String firstname,String lastname) throws Exception;
}
