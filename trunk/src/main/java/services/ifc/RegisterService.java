package services.ifc;

import models.WebResponse;
import models.ws.UserWSNEntity;

/**
 * Created by ivald79 on 24/09/2014.
 */
public interface RegisterService {

    public WebResponse registration(UserWSNEntity userEntity) throws Exception;
}
