package by.bsu.grabar.manager;

import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 */
public class ConfigurationManager {

    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;

    /**
     * The constant DATABASE_URL.
     */
    public static final String DATABASE_URL ="database.url";
    /**
     * The constant LOGIN.
     */
    public static final String LOGIN ="database.login";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD ="database.password";
    /**
     * The constant POOL_SIZE.
     */
    public static final String POOL_SIZE ="database.pool.size";

    private static final String PROP_PATH = "prop/config";

    private final static Logger LOG = Logger.getLogger(ConfigurationManager.class);


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            try {
                instance.resourceBundle = ResourceBundle.getBundle(PROP_PATH);
            }catch (MissingResourceException e){
                LOG.fatal("MissingResourceException");
                throw new RuntimeException();
            }
        }
        return instance;
    }

    /**
     * Gets property.
     *
     * @param key the key
     * @return the property
     */
    public String getProperty(String key) {
        try {
            return resourceBundle.getString(key);
        }catch (MissingResourceException e){
            LOG.fatal("MissingResourceException");
            throw new RuntimeException();
        }
    }
}
