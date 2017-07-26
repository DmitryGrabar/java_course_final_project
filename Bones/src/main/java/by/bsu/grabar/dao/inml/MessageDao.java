package by.bsu.grabar.dao.inml;

import by.bsu.grabar.dao.IMessageDao;
import by.bsu.grabar.entity.Message;
import by.bsu.grabar.error.ConnectionPoolException;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.pool.ConnectionPool;
import by.bsu.grabar.pool.ProxyConnection;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Message dao.
 */
public class MessageDao implements IMessageDao {
    private final static Logger LOG = Logger.getLogger(MessageDao.class);

    private static MessageDao instance;

    private MessageDao() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MessageDao getInstance() {
        if (instance == null) {
            instance = new MessageDao();
        }
        return instance;
    }

    private static final String INSERT_MESSAGE =
            "INSERT INTO message (text, date, user_id)" +
                    " VALUES(?,?,?)";

    private static final String FIND_ALL =
            "SELECT * FROM message";

    private static final String DELETE_MESSAGE =
            "DELETE FROM message WHERE text = ? and user_id = ?";

    public boolean add(Message entity) throws DAOException {
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(INSERT_MESSAGE)
        ) {
            statement.setString(1, entity.getText());
            statement.setTimestamp(2, Timestamp.valueOf(entity.getDate()));
            statement.setInt(3, entity.getUserId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("Add method SQLException.");
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            LOG.error("ConnectionPool exception.");
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Find all list.
     *
     * @return the list
     * @throws DAOException the dao exception
     */
    public List<Message> findAll() throws DAOException {
        List<Message> list = new ArrayList<>();
        ResultSet resultSet;
        Message message;
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(FIND_ALL)
        ) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setText(resultSet.getString("text"));
                message.setDate(LocalDateTime.ofInstant(resultSet.getTimestamp("date").toInstant(),ZoneId.systemDefault()));
                message.setUserId(resultSet.getInt("user_id"));
                list.add(message);
            }
        } catch (SQLException ex) {
            LOG.error("FindAll method SQLException.");
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            LOG.error("ConnectionPool exception.");
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return list;
    }

    /**
     * Delete message boolean.
     *
     * @param text   the text
     * @param userId the user id
     * @return the boolean
     * @throws DAOException the dao exception
     */
    public boolean deleteMessage(String text, Integer userId) throws DAOException {
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(DELETE_MESSAGE)
        ) {
            statement.setString(1, text);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("DeleteMessage method SQLException.");
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            LOG.error("ConnectionPool exception.");
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }
}
