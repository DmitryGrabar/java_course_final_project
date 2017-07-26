import by.bsu.grabar.error.ConnectionPoolException;
import by.bsu.grabar.pool.ConnectionPool;
import by.bsu.grabar.pool.ProxyConnection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class ConnectionPoolTest {

    private static final int POOL_SIZE = 10;

    @Test
    public void poolInit() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Assert.assertTrue(pool.size() == POOL_SIZE);
    }

    @Test
    public void takingConnectionTest() {
        try {
            ProxyConnection connection = ConnectionPool.getInstance().takeConnection();
            Assert.assertTrue(ConnectionPool.getInstance().size() == POOL_SIZE - 1);
            connection.close();
            Assert.assertTrue(ConnectionPool.getInstance().size() == POOL_SIZE);
        } catch (ConnectionPoolException ex) {
            Assert.fail("ConnectionPool Exception!!!");
        } catch (SQLException ex) {
            Assert.fail("SQLException!!!");
        }
    }
}
