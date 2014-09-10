package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constant.CommonConstant;
import constant.ResponseError;
import exceptions.LoginException;
import models.UserEntity;
import models.WebResponse;
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
    public WebResponse authentication(@PathParam("username") String username,
                                           @PathParam("password") String password) throws Exception {
        WebResponse response = new WebResponse();

        try {
            if(username == null || password == null) {
                response.setResponseCode(ResponseError.FAILED);
                response.setResponseMessage("One of two parameters is empty.");
                LOGGER.warn("One of two parameters is empty.");
            } else {
                UserEntity user = loginService.findEntityByUserNameAndPass(username);
                Gson gson = new GsonBuilder().create();
                if (user != null && user.isPasswordEqual(password)) {
                    response.setResponseCode(ResponseError.SUCCESS);
                    response.setResponseMessage(gson.toJson(user));
                    request.getSession(true);
                    request.setAttribute(CommonConstant.USER_TICKET, user);
                    LOGGER.info("Data of user " + user.getUserName() + " was saved in Web Session Ticket");
                } else {
                    response.setResponseCode(ResponseError.FAILED);
                    response.setResponseMessage("The password of " + username + " is incorrect.");
                    LOGGER.warn("The password of " + username + " is incorrect.");
                }
            }
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new LoginException(e.getMessage());
        }

        return response;
    }
}
