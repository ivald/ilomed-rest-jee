package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constant.CommonConstant;
import constant.ResponseError;
import exceptions.WebResponseException;
import models.UserEntity;
import models.WebResponse;
import org.apache.log4j.Logger;
import services.ifc.LoginService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * Created by ivald79 on 17/06/2014.
 */
@Path("/login")
@Named
public class LoginController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @Inject
    private LoginService loginService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authentication/username/{username}/password/{password}")
    public WebResponse authentication(@PathParam("username") String username,
                                           @PathParam("password") String password) throws Exception {
        WebResponse response = new WebResponse();

        try {
            if(username == null || password == null) {
                response.setResponseCode(ResponseError.FAILED);
                message = "One of two parameters is empty.";
                response.setResponseMessage(message);
                LOGGER.warn(message);
            } else {
                UserEntity user = loginService.findEntityByUserNameAndPass(username);
                Gson gson = new GsonBuilder().create();
                if (user != null && user.isPasswordEqual(password)) {
                    response.setResponseCode(ResponseError.SUCCESS);
                    response.setResponseMessage(gson.toJson(user));
                    HttpSession session = request.getSession(true);
                    session.setAttribute(CommonConstant.USER_TICKET, user);
                    session.setMaxInactiveInterval(CommonConstant.SESSION_TIME_OUT);
                    message = "Data of user " + user.getUserName() + " was saved in Web Session Ticket";
                    LOGGER.info(message);
                } else {
                    response.setResponseCode(ResponseError.FAILED);
                    message = "The password of " + username + " is incorrect.";
                    response.setResponseMessage(message);
                    LOGGER.warn(message);
                }
            }
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new WebResponseException(e.getMessage());
        }

        return response;
    }
}
