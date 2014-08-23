package services.impl;

import models.UserEntity;
import repository.ifc.LoginRepository;
import services.ifc.LoginService;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * Created by ivald79 on 16/06/2014.
 */
@Stateless
@Default
public class LoginServiceImpl implements LoginService {

    @Inject
    private LoginRepository loginRepository;

    @Override
    public UserEntity findEntityByUserNameAndPass(String username) {
        return loginRepository.findEntityByUserNameAndPass(username);
    }
}
