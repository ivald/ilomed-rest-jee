package services.ifc;

import models.UserEntity;
import repository.ifc.BaseRepository;

import java.util.List;

public interface UserService extends BaseRepository<UserEntity> {

    public List<UserEntity> getAll();

    public UserEntity find(String name);

    public void delete(String name);
}
