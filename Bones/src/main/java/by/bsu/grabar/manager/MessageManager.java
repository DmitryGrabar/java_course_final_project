package by.bsu.grabar.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The enum Message manager.
 */
public enum MessageManager {

    /**
     * The En.
     */
    EN(ResourceBundle.getBundle("pagecontent", new Locale("en", "EN"))),
    /**
     * The Ru.
     */
    RU(ResourceBundle.getBundle("pagecontent", new Locale("ru", "RU")));

    private static final String EN_LOCALE="en_EN";
    private static final String RU_LOCALE="ru_RU";

    private ResourceBundle resourceBundle;

    /**
     * The constant PARAM_NAME_USER.
     */
    public static final String PARAM_NAME_USER = "user";
    /**
     * The constant PARAM_NAME_USERS.
     */
    public static final String PARAM_NAME_USERS = "users";
    /**
     * The constant PARAM_NAME_MESSAGE.
     */
    public static final String PARAM_NAME_MESSAGE = "message";
    /**
     * The constant PARAM_NAME_MESSAGES.
     */
    public static final String PARAM_NAME_MESSAGES = "messages";
    /**
     * The constant PARAM_NAME_EMAIL.
     */
    public static final String PARAM_NAME_EMAIL = "email";
    /**
     * The constant PARAM_NAME_PASSWORD.
     */
    public static final String PARAM_NAME_PASSWORD = "password";
    /**
     * The constant PARAM_NAME_ADMIN.
     */
    public static final String PARAM_NAME_ADMIN = "admin";
    /**
     * The constant PARAM_LANG.
     */
    public static final String PARAM_LANG = "language";
    /**
     * The constant PARAM_ERROR_MESSAGE.
     */
    public static final String PARAM_ERROR_MESSAGE = "errorMessage";

    /**
     * The constant REGISTRATION_WAS_INTERRUPT_MESSAGE.
     */
    public static final String REGISTRATION_WAS_INTERRUPT_MESSAGE = "not.equals.pass";
    /**
     * The constant EMAIL_IS_ALREADY_USED.
     */
    public static final String EMAIL_IS_ALREADY_USED = "email.is.already.used";
    /**
     * The constant LOGIN_ERROR_MESSAGE.
     */
    public static final String LOGIN_ERROR_MESSAGE = "login.error.message";
    /**
     * The constant INCORRECT_SUM.
     */
    public static final String INCORRECT_SUM = "incorrect.sum";

    /**
     * The constant BANNED.
     */
    public static final String BANNED = "user.banned";

    /**
     * The constant LOGIC_EXCEPTION_ERROR_MESSAGE.
     */
    public static final String LOGIC_EXCEPTION_ERROR_MESSAGE = "logic.exception.error.message";
    /**
     * The constant MESSAGE_WASNT_SENT.
     */
    public static final String MESSAGE_WASNT_SENT = "error.message.wasnt.sent";

    MessageManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Gets manager by locale.
     *
     * @param locale the locale
     * @return the manager by locale
     */
    public static MessageManager getManagerByLocale(String locale) {
        MessageManager messageManager = null;
        if (locale != null) {
            switch (locale.trim()) {
                case EN_LOCALE:
                    messageManager =  MessageManager.EN;
                    break;
                case RU_LOCALE:
                    messageManager = MessageManager.RU;
                    break;
                default:
                    messageManager = MessageManager.EN;
            }
        } else {
            messageManager = MessageManager.EN;
        }
        return messageManager;
    }

    /**
     * Gets property.
     *
     * @param key the key
     * @return the property
     */
    public String getProperty(String key) {
        return (String)resourceBundle.getObject(key);
    }
}

