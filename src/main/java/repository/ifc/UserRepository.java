package repository.ifc;

import models.UserEntity;

import java.util.List;

public interface UserRepository {

    public List<UserEntity> getAll();

    public UserEntity find(Long id);

    public UserEntity find(String name);

    public void save(UserEntity userEntity);

    public void delete(Long id);

    public void delete(String name);

}
