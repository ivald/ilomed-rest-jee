package services;

import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserService {
    @PersistenceContext(name = "default")
    private EntityManager em;

    public List<User> getAll() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        //CriteriaQuery<User> query = em.getCriteriaBuilder().createQuery(User.class);
        //Root<User> root = query.from(User.class);
        //query.select(root);
        //return em.createQuery(query).getResultList();
        return query.getResultList();
    }

    public User find(String name) {
        return em.find(User.class, name);
    }

    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    public void deleteUser(String email) {
        User user = em.find(User.class, email);
        em.remove(user);
    }
}
