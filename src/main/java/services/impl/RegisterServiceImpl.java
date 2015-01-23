package services.impl;

import models.WebResponse;
import models.ws.UserWSNEntity;
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

    public WebResponse registration(UserWSNEntity userEntity) throws Exception {
        return registerRepository.registration(userEntity);
    }
}
