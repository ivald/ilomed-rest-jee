package services.ifc;

import models.UserEntity;
import repository.ifc.BaseIfc;

import java.util.List;

public interface UserService extends BaseIfc<UserEntity> {

    public List<UserEntity> getAll();

    public UserEntity find(String name);

    public void delete(String name);
}
