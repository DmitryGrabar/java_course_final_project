package by.bsu.grabar.logic;

import by.bsu.grabar.dao.inml.UserDao;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import by.bsu.grabar.util.Encryption;
import by.bsu.grabar.util.Validator;
import org.apache.log4j.Logger;

/**
 * The type Account service.
 */
public class AccountService {
    /**
     * The constant LOG.
     */
    final static Logger LOG = Logger.getLogger(AccountService.class);

    /**
     * Authenticate by email and pass user.
     *
     * @param email the email
     * @param pass  the pass
     * @return the user
     * @throws ServiceException the service exception
     */
    public static User authenticateByEmailAndPass(String email, String pass) throws ServiceException {
        User user = null;
        if (Validator.checkEmailAndPassword(email, pass)) {
            try {
                pass = Encryption.encrypt(pass);
                user = UserDao.getInstance().findByEmailAndPassword(email, pass);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            user = null;
        }
        return user;
    }

    /**
     * Authenticate by email user.
     *
     * @param email the email
     * @return the user
     * @throws ServiceException the service exception
     */
    public static User authenticateByEmail(String email) throws ServiceException {
        User user;
        if (Validator.checkEmail(email)) {
            try {
                user = UserDao.getInstance().findByEmail(email);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        } else {
            user = null;
        }
        return user;
    }

    /**
     * Add account.
     *
     * @param user the user
     * @throws ServiceException the service exception
     */
    public static void addAccount(User user) throws ServiceException {
        try {
            UserDao.getInstance().add(user);
        } catch (DAOException e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * Check pass equals boolean.
     *
     * @param pass         the pass
     * @param confirm_pass the confirm pass
     * @return the boolean
     */
    public static boolean checkPassEquals(String pass, String confirm_pass){
        return Validator.checkPassword(pass) && pass.equals(confirm_pass);
    }
}
