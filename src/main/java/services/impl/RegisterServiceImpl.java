package services.impl;

import models.UserEntity;
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

    public void save(UserEntity entity) throws Exception {
        registerRepository.save(entity);
    }

    public boolean isUserNameValid(String username) {
        return registerRepository.isUserNameValid(username);
    }
}
