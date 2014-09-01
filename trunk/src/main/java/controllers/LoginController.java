package controllers;

import constant.CommonConstant;
import exceptions.LoginException;
import models.UserEntity;
import org.apache.log4j.Logger;
import services.ifc.LoginService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ivald79 on 17/06/2014.
 */
@Path("/login")
@Named
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @Inject
    private LoginService loginService;

    @Context
    private HttpServletRequest request;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/path/username/{username}/password/{password}")
    public List<UserEntity> authentication(@PathParam("username") String username,
                                                @PathParam("password") String password) throws Exception {
        List<UserEntity> response = new ArrayList<UserEntity>();

        try {
            UserEntity user = loginService.findEntityByUserNameAndPass(username);

            if (user != null && user.isPasswordEqual(password)) {
                response.add(user);
                request.getSession(true);
                request.setAttribute(CommonConstant.USER_TICKET, user);
                LOGGER.info("Data of user " + user.getUserName() + " was saved in Web Session Ticket");
            }
        }catch (Exception e) {
            LOGGER.warn(e.getMessage());
            throw new LoginException(e.getMessage());
        }

        return response;
    }
}
