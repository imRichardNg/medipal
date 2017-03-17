package iss.nus.medipal.dao;

import java.util.List;

/**
 * Created by richard on 4/3/17.
 */

public interface DAOInterface<T> {
    long save(T t);
    long update(T t);
    long delete(T t);
    T get(T t);
    List<T> getAll();
}
