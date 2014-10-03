package services.impl;

import models.UserEntity;
import repository.ifc.LoginRepository;
import services.ifc.LoginService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by ivald79 on 16/06/2014.
 */
@Stateless
public class LoginServiceImpl implements LoginService {

    @Inject
    private LoginRepository loginRepository;

    public UserEntity findEntityByUserNameAndPass(String username) throws Exception {
        return loginRepository.findEntityByUserNameAndPass(username);
    }
}
