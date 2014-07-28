package repository.impl;

import repository.ifc.BaseIfc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseRepositoryImpl<E> implements BaseIfc<E> {
    @PersistenceContext
    protected EntityManager em;
    private final Class<E> entityClass;

    public BaseRepositoryImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public E find(Long id) {
        return em.find(entityClass, id);
    }

    public void save(E entity) {
        em.merge(entity);
    }

    public void delete(E entity) {
        em.remove(entity);
    }

    public void delete(Long id) {
        em.remove(find(id));
    }
}
