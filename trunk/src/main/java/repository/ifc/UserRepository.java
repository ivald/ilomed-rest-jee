package repository.ifc;

import models.UserEntity;

import java.util.List;

public interface UserRepository extends BaseRepository<UserEntity> {

    public List<UserEntity> getAll();

    public UserEntity find(String name);

    public void delete(String name);

}
