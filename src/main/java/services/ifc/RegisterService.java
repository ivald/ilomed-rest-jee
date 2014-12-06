package services.ifc;

import models.UserEntity;
import models.WebResponse;
import repository.ifc.BaseIfc;

import javax.ws.rs.PathParam;

/**
 * Created by ivald79 on 24/09/2014.
 */
public interface RegisterService {

    public WebResponse registration(String username,String password,String firstname,String lastname) throws Exception;
}
