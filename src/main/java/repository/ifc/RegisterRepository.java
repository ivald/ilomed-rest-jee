package repository.ifc;

import models.UserEntity;
import models.WebResponse;
import models.ws.UserWSNEntity;

/**
 * Created by ivald79 on 24/09/2014.
 */
public interface RegisterRepository extends BaseIfc<UserEntity> {

    boolean isUserNameValid(String username);

    public WebResponse registration(UserWSNEntity userEntity) throws Exception;
}
