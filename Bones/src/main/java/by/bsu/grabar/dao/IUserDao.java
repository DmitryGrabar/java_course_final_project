package by.bsu.grabar.dao;

import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;

import java.util.List;

/**
 * The interface User dao.
 */
public interface IUserDao extends GenericDao<Integer, User>{

    @Override
    public boolean add(User entity) throws DAOException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DAOException the dao exception
     */
    List<User> findAll() throws DAOException;

    /**
     * Find by email user.
     *
     * @param login the login
     * @return the user
     * @throws DAOException the dao exception
     */
    User findByEmail(String login) throws DAOException;

    /**
     * Find by email and password user.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws DAOException the dao exception
     */
    User findByEmailAndPassword(String login, String password) throws DAOException;

    /**
     * Recovery user password boolean.
     *
     * @param login       the login
     * @param newPassword the new password
     * @return the boolean
     * @throws DAOException the dao exception
     */
}
