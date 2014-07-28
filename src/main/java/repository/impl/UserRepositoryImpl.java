package repository.impl;

import models.UserEntity;
import repository.ifc.UserRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepositoryImpl extends BaseRepositoryImpl<UserEntity> implements UserRepository {

    public UserRepositoryImpl() {
        super(UserEntity.class);
    }

    public List<UserEntity> getAll() {
        TypedQuery<UserEntity> query = em.createQuery("select u from UserEntity u", UserEntity.class);
        //CriteriaQuery<UserEntity> query = em.getCriteriaBuilder().createQuery(UserEntity.class);
        //Root<UserEntity> root = query.from(UserEntity.class);
        //query.select(root);
        //return em.createQuery(query).getResultList();
        return query.getResultList();
    }

    public UserEntity find(String name) {
        return em.find(UserEntity.class, name);
    }

    public void delete(String name) {
        em.remove(find(name));
    }
}


