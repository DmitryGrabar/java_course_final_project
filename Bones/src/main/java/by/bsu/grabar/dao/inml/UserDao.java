package by.bsu.grabar.dao.inml;

import by.bsu.grabar.dao.IUserDao;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.entity.UserEnum;
import by.bsu.grabar.error.ConnectionPoolException;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.pool.ConnectionPool;
import by.bsu.grabar.pool.ProxyConnection;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User dao.
 */
public class UserDao implements IUserDao {

    private final static Logger LOG = Logger.getLogger(UserDao.class);

    private static UserDao instance;

    private static final String FIND_ALL_USERS = "SELECT login, money, email, rating, ban " +
            "FROM user WHERE role='user'";

    private static final String UPDATE_MONEY_AND_RATING =
            "UPDATE user SET money = ?, rating = ? WHERE user_id = ?";

    private static final String INSERT_USER =
            "INSERT INTO user (login, password, email)" +
                    " VALUES(?,?,?)";

    private static final String ADD_WIN =
            "UPDATE user " +
                    " SET money = ?, all_games = all_games+1, win_games = win_games+1, rating = ? " +
                    "WHERE user_id = ?";

    private static final String ADD_LOSE =
            "UPDATE user " +
                    " SET money = ?, all_games = all_games+1, rating = ?" +
                    "WHERE user_id = ?";

    private static final String SET_PHOTO =
            "UPDATE user SET imgpath=? WHERE email=?";

    private static final String FIND_BY_EMAIL =
            "SELECT user_id, login, email, password, ban, money, role, rating, imgpath, all_games, win_games" +
                    " FROM user WHERE email = ?";

    private static final String FIND_BY_EMAIL_AND_PASSWORD =
            "SELECT *" +
                    " FROM user WHERE email = ? AND password = ?";

    private static final String BAN_UNBAN = "UPDATE user SET ban = ? WHERE user_id = ?";

    private UserDao() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet;
        User user;
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(FIND_ALL_USERS)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setMoney(resultSet.getBigDecimal("money"));
                user.setLogin(resultSet.getString("login"));
                user.setRating(resultSet.getDouble("rating"));
                user.setBan(resultSet.getBoolean("ban"));
                users.add(user);
            }
        } catch (SQLException ex) {
            LOG.error("FindAll user method SQLException");
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            LOG.error("ConnectionPool exception");
            throw new DAOException("ConnectionPool exception!", ex);
        }
        LOG.info("Find all users in database");
        return users;
    }

    /**
     * Update score and rating boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DAOException the dao exception
     */
    public boolean updateScoreAndRating(User user) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(UPDATE_MONEY_AND_RATING)
        ) {
            statement.setBigDecimal(1, user.getMoney());
            statement.setDouble(2, user.getRating());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }
        LOG.info(user.getEmail() + " - Updated score and rating");
        return true;
    }

    public User findByEmail(String email) throws DAOException {
        User user = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(FIND_BY_EMAIL);
        ) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("user_id")));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBan(resultSet.getBoolean("ban"));
                user.setMoney(resultSet.getBigDecimal("money"));
                user.setRole(UserEnum.valueOf(resultSet.getString("role").toUpperCase()));
                user.setRating(resultSet.getDouble("rating"));
                user.setImgPath((resultSet.getString("imgpath")));
                user.setAllGames(resultSet.getInt("all_games"));
                user.setWinGames(resultSet.getInt("win_games"));
            }
        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return user;
    }

    /**
     * Change user photo boolean.
     *
     * @param email      the email
     * @param photo_path the photo path
     * @return the boolean
     * @throws DAOException the dao exception
     */
    public boolean changeUserPhoto(String email, String photo_path) throws DAOException {
        try (ProxyConnection ProxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = ProxyConnection.prepareStatement(SET_PHOTO)
        ) {
            statement.setString(1, photo_path);
            statement.setString(2, email);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    public User findByEmailAndPassword(String email, String password) throws DAOException {
        User user = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD);
        ) {
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("user_id")));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setBan(resultSet.getBoolean("ban"));
                user.setMoney(resultSet.getBigDecimal("money"));
                user.setRole(UserEnum.valueOf(resultSet.getString("role").toUpperCase()));
                user.setRating(resultSet.getDouble("rating"));
                user.setImgPath(resultSet.getString("imgpath"));
                user.setAllGames(resultSet.getInt("all_games"));
                user.setWinGames(resultSet.getInt("win_games"));
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return user;
    }

    @Override
    public boolean add(User entity) throws DAOException {
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(INSERT_USER)
        ) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Add win boolean.
     *
     * @param user the user
     * @param bet  the bet
     * @return the boolean
     * @throws DAOException the dao exception
     */
    public boolean addWin(User user, BigDecimal bet) throws DAOException {
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(ADD_WIN)
        ) {
            statement.setBigDecimal(1, user.getMoney().add(bet));
            statement.setInt(2, user.getId());
            statement.setDouble(3, user.getRating());
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Add lose boolean.
     *
     * @param user the user
     * @param bet  the bet
     * @return the boolean
     * @throws DAOException the dao exception
     */
    public boolean addLose(User user, BigDecimal bet) throws DAOException {
        try (ProxyConnection proxy = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxy.prepareStatement(ADD_LOSE)
        ) {
            statement.setBigDecimal(1, user.getMoney().subtract(bet));
            statement.setInt(2, user.getId());
            statement.setDouble(3, user.getRating());
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Ban user boolean.
     *
     * @param user the user
     * @return the boolean
     * @throws DAOException the dao exception
     */
    public boolean banUser(User user) throws DAOException {
        try (ProxyConnection ProxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = ProxyConnection.prepareStatement(BAN_UNBAN)
        ) {
            statement.setBoolean(1, !user.getBan());
            statement.setInt(2, user.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }
}