package services.impl;

import models.UserEntity;
import models.WebResponse;
import repository.ifc.RegisterRepository;
import services.ifc.RegisterService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by ivald79 on 24/09/2014.
 */
@Stateless
public class RegisterServiceImpl implements RegisterService {

    @Inject
    private RegisterRepository registerRepository;

    public WebResponse registration(String username,String password,String firstname,String lastname) throws Exception {
        return registerRepository.registration(username, password, firstname, lastname);
    }
}
