package by.bsu.grabar.logic;

import by.bsu.grabar.dao.inml.MessageDao;
import by.bsu.grabar.entity.Message;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;

/**
 * The type Send message to admin.
 */
public class SendMessageToAdmin {
    /**
     * Send.
     *
     * @param user the user
     * @param text the text
     * @throws ServiceException the service exception
     */
    public static void send(User user, String text) throws ServiceException {
        try {
            MessageDao.getInstance().add(new Message(user.getId(), text));
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }
    }

}

