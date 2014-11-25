package ws.ifc;

import models.WebResponse;
import models.ws.UserWSNEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by ivald79 on 23/10/2014.
 */
@WebService(name = "RegisterUser")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface RegisterWS {
    @WebMethod
    @WebResult(partName = "WebResponse")
    public String registration(@WebParam(partName = "User") UserWSNEntity userWSNEntity) throws Exception;
}
