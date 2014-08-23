package controllers;

import models.UserEntity;
import services.ifc.LoginService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ivald79 on 17/06/2014.
 */
@Path("/login")
@Named
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @Inject
    private LoginService loginService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/path/username/{username}/password/{password}")
    public List<UserEntity> getAllGoals(@PathParam("username") String username,
                                        @PathParam("password") String password) throws Exception {

        List<UserEntity> response = new ArrayList<UserEntity>();
        UserEntity user = loginService.findEntityByUserNameAndPass(username);
        if(user != null && user.isPasswordEqual(password)) {
            response.add(user);
        }
        LOGGER.info("Test ilyav");
        return response;
    }
}
