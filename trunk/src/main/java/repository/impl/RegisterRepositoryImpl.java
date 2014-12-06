package repository.impl;

import constant.ResponseError;
import models.ContactEntity;
import models.UserEntity;
import models.WebResponse;
import org.apache.log4j.Logger;
import repository.ifc.RegisterRepository;
import services.ifc.LoginService;
import services.ifc.RegisterService;
import services.impl.SequenceGeneratorServiceImpl;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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
    public WebResponse registration(String username, String password, String firstname, String lastname) throws Exception {

        WebResponse response = new WebResponse();

        try {
            if (firstname == null || lastname == null || username == null || password == null) {
                response.setResponseCode(ResponseError.FAILED);
                message = "An one parameter is empty.";
                response.setResponseMessage(message);
                LOGGER.warn(message);
            } else {
                if (isUserNameValid(username)) {
                    UserEntity userEntity = new UserEntity(UserEntity.class);
                    userEntity.setUserName(username);
                    userEntity.setPassword(password);
                    ContactEntity contactEntity = new ContactEntity(ContactEntity.class);
                    contactEntity.setFirstName(firstname);
                    contactEntity.setLastName(lastname);
                    userEntity.setContactEntity(contactEntity);
                    save(userEntity);
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
        }

        return response;
    }
}
