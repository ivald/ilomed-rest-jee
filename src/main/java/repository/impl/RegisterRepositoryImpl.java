package repository.impl;

import constant.ResponseError;
import models.ContactEntity;
import models.UserEntity;
import models.WebResponse;
import models.ws.UserWSNEntity;
import org.apache.log4j.Logger;
import repository.ifc.RegisterRepository;
import services.ifc.LoginService;

import javax.inject.Inject;

/**
 * Created by ivald79 on 24/09/2014.
 */
public class RegisterRepositoryImpl extends BaseRepositoryImpl<UserEntity> implements RegisterRepository {

    private static final Logger LOGGER = Logger.getLogger(RegisterRepositoryImpl.class.getName());

    @Inject
    private LoginService loginService;

    public RegisterRepositoryImpl() {
        super(UserEntity.class);
    }

    @Override
    public boolean isUserNameValid(String username) {
        try {
            loginService.findEntityByUserNameAndPass(username);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public WebResponse registration(UserWSNEntity userEntity) throws Exception {

        WebResponse response = new WebResponse();

        try {
            if (userEntity.getContactWSNEntity() == null ||
                    (userEntity.getContactWSNEntity().getFirstName() == null ||
                            userEntity.getContactWSNEntity().getLastName() == null ||
                            userEntity.getUserName() == null ||
                            userEntity.getPassword() == null)) {
                response.setResponseCode(ResponseError.FAILED);
                message = "An one parameter is empty.";
                response.setResponseMessage(message);
                LOGGER.warn(message);
            } else {
                if (isUserNameValid(userEntity.getUserName())) {
                    UserEntity user = new UserEntity(UserEntity.class);
                    user.setUserName(userEntity.getUserName());
                    user.setPassword(userEntity.getPassword());
                    ContactEntity contactEntity = new ContactEntity(ContactEntity.class);
                    contactEntity.setFirstName(userEntity.getContactWSNEntity().getFirstName());
                    contactEntity.setLastName(userEntity.getContactWSNEntity().getLastName());
                    user.setContactEntity(contactEntity);
                    save(user);
                    response.setResponseCode(ResponseError.SUCCESS);
                    message = "User " + user.getUserName() + " was registered successfully.";
                    response.setResponseMessage(message);
                    LOGGER.info(message);
                } else {
                    response.setResponseCode(ResponseError.FAILED);
                    message = "The user name: " + userEntity.getUserName() + " already exists. Please try again.";
                    response.setResponseMessage(message);
                    LOGGER.warn(message);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return response;
    }
}
