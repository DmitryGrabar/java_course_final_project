package by.bsu.grabar.dao;

import by.bsu.grabar.error.DAOException;

/**
 * The interface Generic dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface GenericDao <K,T>{

    /**
     * Add boolean.
     *
     * @param entity the entity
     * @return the boolean
     * @throws DAOException the dao exception
     */
    boolean add(T entity) throws DAOException;
}
