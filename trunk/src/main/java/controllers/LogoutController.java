package controllers;

import constant.CommonConstant;
import constant.ResponseError;
import exceptions.WebResponseException;
import models.UserEntity;
import models.WebResponse;
import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ivald79 on 12/21/2014.
 */
@Path("/logout")
@Named
public class LogoutController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(LogoutController.class.getName());

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WebResponse logOut() throws Exception {
        WebResponse response = new WebResponse();

        try {
            response.setResponseCode(ResponseError.SUCCESS);
            HttpSession session = request.getSession(true);
            UserEntity user = (UserEntity) session.getAttribute(CommonConstant.USER_TICKET);
            session.invalidate();
            message = "Data of user " + user.getUserName() + " was removed due to logout.";
            LOGGER.info(message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new WebResponseException(e.getMessage());
        }

        return response;
    }
}
