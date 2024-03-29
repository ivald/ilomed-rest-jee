package ws.impl;

import exceptions.WebResponseException;
import models.WebResponse;
import models.ws.UserWSNEntity;
import org.apache.log4j.Logger;
import services.ifc.RegisterService;
import ws.ifc.RegisterWS;

import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by ivald79 on 23/10/2014.
 */
@WebService(endpointInterface = "ws.ifc.RegisterWS", portName = "RegisterUserPort", serviceName = "RegisterUserService")
public class RegisterWSImpl extends BaseWS implements RegisterWS {

    private static final Logger LOGGER = Logger.getLogger(RegisterWSImpl.class.getName());

    @Inject
    private RegisterService registerService;

    @Override
    @WebResult(partName = "WebResponse")
    public String registration(@WebParam(partName = "User") UserWSNEntity userWSNEntity) throws Exception {
        WebResponse response = null;

        try {
            response = registerService.registration(userWSNEntity);
            message = response.getResponseMessage();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new WebResponseException(e.getMessage());
        }

        return message;
    }
}
