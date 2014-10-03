package controllers;

import constant.ResponseError;
import exceptions.WebResponseException;
import models.ContactEntity;
import models.UserEntity;
import models.WebResponse;
import org.apache.log4j.Logger;
import services.ifc.RegisterService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ivald79 on 23/09/2014.
 */
@Path("/register")
@Named
public class RegisterController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());

    @Inject
    private RegisterService registerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registration/username/{username}/password/{password}/firstname/{firstname}/lastname/{lastname}")
    public WebResponse registration(@PathParam("username") String username,
                                    @PathParam("password") String password,
                                    @PathParam("firstname") String firstname,
                                    @PathParam("lastname") String lastname) throws Exception {
        WebResponse response = new WebResponse();

        try {
            if (firstname == null || lastname == null || username == null || password == null) {
                response.setResponseCode(ResponseError.FAILED);
                message = "An one parameter is empty.";
                response.setResponseMessage(message);
                LOGGER.warn(message);
            } else {
                if (registerService.isUserNameValid(username)) {
                    UserEntity userEntity = new UserEntity(UserEntity.class);
                    userEntity.setUserName(username);
                    userEntity.setPassword(password);
                    ContactEntity contactEntity = new ContactEntity(ContactEntity.class);
                    contactEntity.setFirstName(firstname);
                    contactEntity.setLastName(lastname);
                    userEntity.setContactEntity(contactEntity);
                    registerService.save(userEntity);
                    response.setResponseCode(ResponseError.SUCCESS);
                    message = "User " + userEntity.getUserName() + " was registered successfully.";
                    response.setResponseMessage(message);
                    LOGGER.info(message);
                } else {
                    response.setResponseCode(ResponseError.FAILED);
                    message = "The user name: " + username + " already exists. Please try again.";
                    response.setResponseMessage(message);
                    LOGGER.warn(message);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new WebResponseException(e.getMessage());
        }

        return response;
    }

}
