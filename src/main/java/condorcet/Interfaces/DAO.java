package condorcet.Interfaces;

import java.util.List;

public interface DAO<T> {
    T findById(int id);

    void saveEntity(T entity);

    void deleteEntity(T entity);

    void updateEntity(T entity);

    List<T> findAll();
}
