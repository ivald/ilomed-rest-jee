package repository.ifc;

public interface BaseRepository<E> {

    public E find(Long id);

    public void save(E entity);

    public void delete(E entity);

    public void delete(Long id);
}
