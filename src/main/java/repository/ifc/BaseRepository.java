package repository.ifc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface BaseRepository<E> {

    public E find(Long id);

    public void save(E entity);

    public void delete(E entity);

    public void delete(Long id);
}
