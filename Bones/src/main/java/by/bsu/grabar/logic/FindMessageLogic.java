package by.bsu.grabar.logic;

import by.bsu.grabar.dao.inml.MessageDao;
import by.bsu.grabar.entity.Message;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Find message logic.
 */
public class FindMessageLogic {
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(FindUserLogic.class);

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    public static List<Message> findAll() throws ServiceException {
        List<Message> messages;
        try {
            messages = MessageDao.getInstance().findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return messages;
    }
}
