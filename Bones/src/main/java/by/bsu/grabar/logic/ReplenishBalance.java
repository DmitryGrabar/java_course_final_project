package by.bsu.grabar.logic;

import by.bsu.grabar.dao.inml.UserDao;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import by.bsu.grabar.manager.MessageManager;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * The type Replenish balance.
 */
public class ReplenishBalance {
    private static final String PARAM_SUM_VALIDATION = "[1-9][0-9]{0,5}";
    private static final String ADD_BALANCE = "addBalance";
    private static final String RADUCE_BALANCE = "reduceBalance";

    /**
     * Replenish balance.
     *
     * @param user        the user
     * @param validSum    the valid sum
     * @param locale      the locale
     * @param commandName the command name
     * @throws ServiceException the service exception
     */
    public static void replenishBalance(User user, String validSum, String locale, String commandName) throws ServiceException {
        try {
            validation(validSum, locale);
            BigDecimal sum = new BigDecimal(validSum);
            BigDecimal currentBalance = null;
            if (commandName.equals(ADD_BALANCE.toUpperCase())) {
                currentBalance = user.getMoney().add(sum);
            } else if (commandName.equals(RADUCE_BALANCE.toUpperCase())) {
                currentBalance = user.getMoney().subtract(sum);
            }
            user.setMoney(currentBalance);
            UserDao.getInstance().updateScoreAndRating(user);
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }
    }

    private static void validation(String sum, String locale) throws ServiceException {
        validationField(PARAM_SUM_VALIDATION, MessageManager.INCORRECT_SUM, sum, locale);
    }

    private static void validationField(String pattern, String errorMessage, String field, String locale) throws ServiceException {
        if (!Pattern.matches(pattern, field)) {
            throw new ServiceException(MessageManager.getManagerByLocale(locale).getProperty(errorMessage));
        }
    }
}

