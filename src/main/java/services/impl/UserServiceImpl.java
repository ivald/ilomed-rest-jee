package services.impl;

import models.UserEntity;
import repository.ifc.UserRepository;
import services.ifc.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    public List<UserEntity> getAll() {
        return userRepository.getAll();
    }

    public UserEntity find(Long id) {
        return userRepository.find(id);
    }

    @Override
    public UserEntity find(String name) {
        return userRepository.find(name);
    }

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void delete(String name) {
        userRepository.delete(name);
    }
}
