package by.bsu.grabar.logic;

import by.bsu.grabar.dao.inml.UserDao;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Find user logic.
 */
public class FindUserLogic {
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(FindUserLogic.class);

    /**
     * Find user.
     *
     * @param email the email
     * @return the user
     * @throws ServiceException the service exception
     */
    public static User find(String email) throws ServiceException {
        User user;
        try {
            user = UserDao.getInstance().findByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return user;
    }

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    public static List<User> findAll() throws ServiceException {
        List<User> users;
        try {
            users = UserDao.getInstance().findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }
}
