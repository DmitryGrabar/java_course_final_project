package by.bsu.grabar.dao.inml;

import by.bsu.grabar.dao.IGameDao;
import by.bsu.grabar.entity.Game;
import by.bsu.grabar.error.ConnectionPoolException;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.pool.ConnectionPool;
import by.bsu.grabar.pool.ProxyConnection;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * The type Game dao.
 */
public class GameDao implements IGameDao {
    private final static Logger LOG = Logger.getLogger(GameDao.class);

    private static GameDao instance;

    private static final String INSERT_GAME =
            "INSERT INTO game (min_bet, min_points, date, bet)" +
                    " VALUES(?,?,?,?)";

    private GameDao() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GameDao getInstance() {
        if (instance == null) {
            instance = new GameDao();
        }
        return instance;
    }

    @Override
    public boolean add(Game entity) throws DAOException {
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(INSERT_GAME)
        ) {
            statement.setBigDecimal(1, entity.getMin_bet());
            statement.setInt(2, entity.getMin_points());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getDate()));
            statement.setBigDecimal(4, entity.getBet());
            statement.executeUpdate();

        } catch (SQLException ex) {
            LOG.error("Add method resultset problem.");
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            LOG.error("ConnectionPool problem.");
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }
}
