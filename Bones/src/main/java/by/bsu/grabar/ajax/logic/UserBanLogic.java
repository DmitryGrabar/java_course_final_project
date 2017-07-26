package by.bsu.grabar.ajax.logic;

import by.bsu.grabar.dao.inml.UserDao;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * The type User ban logic.
 */
public class UserBanLogic {
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(UserBanLogic.class);

    /**
     * Ban.
     *
     * @param email the email
     * @throws ServiceException the service exception
     */
    public static void ban(String email) throws ServiceException {
        try {
            User user = UserDao.getInstance().findByEmail(email);
            UserDao.getInstance().banUser(user);
            LOG.info("User banned or unbanned: " + user.getEmail());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
