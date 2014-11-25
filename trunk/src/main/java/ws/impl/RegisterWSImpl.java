package ws.impl;

import constant.CommonConstant;
import exceptions.WebResponseException;
import models.ContactEntity;
import models.UserEntity;
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
public class RegisterWSImpl implements RegisterWS {

    private static final Logger LOGGER = Logger.getLogger(RegisterWSImpl.class.getName());

    @Inject
    private RegisterService registerService;

    @Override
    @WebResult(partName = "WebResponse")
    public String registration(@WebParam(partName = "User") UserWSNEntity userWSNEntity) throws Exception {
        String message;

        try {

            String username = userWSNEntity.getUserName();
            String password = userWSNEntity.getPassword();
            String firstName = userWSNEntity.getContactWSNEntity().getFirstName();
            String lastName = userWSNEntity.getContactWSNEntity().getLastName();

            if (firstName == null || lastName == null || username == null || password == null) {
                message = "An one parameter is empty.";
                LOGGER.warn(message);
            } else {
                if (registerService.isUserNameValid(username)) {
                    UserEntity userEntity = new UserEntity(UserEntity.class);
                    userEntity.setUserName(username);
                    userEntity.setPassword(password);
                    ContactEntity contactEntity = new ContactEntity(ContactEntity.class);
                    contactEntity.setFirstName(firstName);
                    contactEntity.setLastName(lastName);
                    userEntity.setContactEntity(contactEntity);
                    registerService.save(userEntity);
                    message = "User " + userEntity.getUserName() + " was registered successfully.";
                    LOGGER.info(message);
                } else {
                    message = "The user name: " + username + " already exists. Please try again.";
                    LOGGER.warn(message);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new WebResponseException(e.getMessage());
        }

        return message;
    }
}
