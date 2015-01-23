package controllers;

import exceptions.WebResponseException;
import models.WebResponse;
import models.ws.UserWSNEntity;
import org.apache.log4j.Logger;
import services.ifc.RegisterService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by ivald79 on 23/09/2014.
 */
@Path("/register")
@Named
@RequestScoped
public class RegisterController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());

    @Inject
    private RegisterService registerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registration")
    public WebResponse registration(UserWSNEntity userWSNEntity) throws Exception {
        WebResponse response = null;

        try {
            response = registerService.registration(userWSNEntity);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new WebResponseException(e.getMessage());
        }

        return response;
    }

}
