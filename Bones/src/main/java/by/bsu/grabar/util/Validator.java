package by.bsu.grabar.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * The type Validator.
 */
public class Validator {
    private static final String LOGIN_REGEX = "[A-z0-9_-]{3,16}";
    private static final String PASSWORD_REGEX = "[A-z0-9_-]{6,18}";
    private static final String EMAIL_REGEX = "[A-z0-9_\\.-]+@[a-z]+\\.[a-z]{2,4}";
    private static final String RATING = "\\d{1,4}";

    /**
     * Check new account boolean.
     *
     * @param login    the login
     * @param password the password
     * @param email    the email
     * @return the boolean
     */
    public static boolean checkNewAccount(String login, String password, String email){
        boolean flag;
        flag = Pattern.matches(LOGIN_REGEX, login)
                && Pattern.matches(PASSWORD_REGEX, password)
                && Pattern.matches(EMAIL_REGEX, email);
        return flag;
    }

    /**
     * Check email and password boolean.
     *
     * @param email    the email
     * @param password the password
     * @return the boolean
     */
    public static boolean checkEmailAndPassword(String email, String password) {
        boolean flag = false;
        if (Pattern.matches(EMAIL_REGEX, email)) {
            flag = Pattern.matches(PASSWORD_REGEX, password);
        }
        return flag;
    }

    /**
     * Check password boolean.
     *
     * @param newPassword the new password
     * @return the boolean
     */
    public static boolean checkPassword(String newPassword) {
        return Pattern.matches(PASSWORD_REGEX, newPassword);
    }

    /**
     * Check email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean checkEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * Check available money boolean.
     *
     * @param amount the amount
     * @param money  the money
     * @return the boolean
     */
    public static boolean checkAvailableMoney(BigDecimal amount, String money){
        //if (amount.compareTo(new BigDecimal(MINIMUM_AMOUNT)) < 0){
        if (amount.compareTo(new BigDecimal(0.0)) < 0){
            return false;
        }
        BigDecimal balance =  new BigDecimal(money);
        return balance.compareTo(amount) >= 0;
    }


    /**
     * Check score boolean.
     *
     * @param score the score
     * @return the boolean
     */
    public static boolean checkScore(String score) {
        return (Pattern.matches(RATING, score));
    }


}
