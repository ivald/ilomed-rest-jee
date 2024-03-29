package repository.ifc;

public interface BaseIfc<E> {

    public E find(Long id);

    public void save(E entity) throws Exception;

    public void delete(E entity);

    public void delete(Long id);
}
