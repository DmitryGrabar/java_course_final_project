package by.bsu.grabar.pool;

import by.bsu.grabar.error.ConnectionPoolException;
import by.bsu.grabar.manager.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Connection pool.
 */
public class ConnectionPool {
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static AtomicBoolean isCreated = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> connections;
    private final String URL;
    private final String LOGIN;
    private final String PASSWORD;


    private ConnectionPool(String url, String login, String password, int initConnectionCount) {
        this.connections = new LinkedBlockingQueue<>();
        this.URL = url;
        this.LOGIN = login;
        this.PASSWORD = password;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOG.fatal("Fatal error when registering DriverManager");
            throw new RuntimeException("Error while driver register.");
        }
        for (int i = 0; i < initConnectionCount; i++)
            try {
                connections.put(getConnection());
            } catch (ConnectionPoolException | InterruptedException e) {
                LOG.error(e.getMessage());
                throw new RuntimeException("Error in putting connection");
            }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                if (!isCreated.get()) {
                    String url = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DATABASE_URL);
                    String login = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN);
                    String password = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PASSWORD);
                    int count = Integer.parseInt(ConfigurationManager.getInstance().getProperty(ConfigurationManager.POOL_SIZE));
                    instance = new ConnectionPool(url, login, password, count);
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private ProxyConnection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Error! Creating pool has been failed!", e);
        }
        return new ProxyConnection(connection);
    }

    /**
     * Take connection proxy connection.
     *
     * @return the proxy connection
     * @throws ConnectionPoolException the connection pool exception
     */
    public ProxyConnection takeConnection() throws ConnectionPoolException {
        ProxyConnection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error! Can't take pool!", e);
        }
        return connection;
    }

    /**
     * Put connection.
     *
     * @param proxyConnection the proxy connection
     */
    public void putConnection(ProxyConnection proxyConnection) {
        connections.add(proxyConnection);
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return connections.size();
    }

    /**
     * Destroy connections.
     */
    public void destroyConnections() {
        ProxyConnection connection;
        int counter = 0;
        for (int i = 0; i < connections.size(); i++) {
            try {
                connection = takeConnection();
                connection.destroy();
                counter++;
            } catch (ConnectionPoolException | SQLException ex) {
                LOG.error("SQLException", ex);
            }
        }
        LOG.info("Closed " + counter + " connections");
    }
}

