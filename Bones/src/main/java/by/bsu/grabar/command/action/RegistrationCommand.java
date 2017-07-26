package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.exception.ServiceException;
import by.bsu.grabar.logic.AccountService;
import by.bsu.grabar.manager.MessageManager;
import by.bsu.grabar.manager.PageManager;
import by.bsu.grabar.util.Encryption;
import by.bsu.grabar.util.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Registration command.
 */
public class RegistrationCommand implements Command {
    /**
     * The constant LOG.
     */
    final static Logger LOG = Logger.getLogger(RegistrationCommand.class);
    private static final String PARAM_NAME_LOGIN = "sign_login";
    private static final String PARAM_NAME_EMAIL = "sign_email";
    private static final String PARAM_NAME_PASSWORD = "sign_pass";
    private static final String PARAM_NAME_CONFIRM_PASSWORD = "confirm_password";

    @Override
    public String execute(HttpServletRequest request) {
        User user;
        String page = null;
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String confirm_pass = request.getParameter(PARAM_NAME_CONFIRM_PASSWORD);
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute(MessageManager.PARAM_LANG);

        if (AccountService.checkPassEquals(pass, confirm_pass)) {
            try {
                if (AccountService.authenticateByEmail(email) == null) {
                    user = createUser(request);
                    AccountService.addAccount(user);
                    session.setAttribute(MessageManager.PARAM_NAME_USER, user);
                    page = PageManager.PROFILE_PAGE;
                } else {
                    request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(locale).getProperty(MessageManager.EMAIL_IS_ALREADY_USED));
                    page = PageManager.LOGIN_PAGE;
                }
            } catch (ServiceException e) {
                LOG.error("Something has gone wrong, redirect to error page.", e);
                request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(locale).getProperty(
                        MessageManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
                page = PageManager.ERROR_PAGE;
            }
        } else {
            request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(locale).getProperty(MessageManager.REGISTRATION_WAS_INTERRUPT_MESSAGE));
            page = PageManager.LOGIN_PAGE;
        }
        return page;
    }

    private User createUser(HttpServletRequest request) throws ServiceException {
        User user = new User();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        if (Validator.checkNewAccount(login, pass, email)) {
            user.setLogin(request.getParameter(PARAM_NAME_LOGIN));
            user.setPassword(Encryption.encrypt(request.getParameter(PARAM_NAME_PASSWORD)));
            user.setEmail(request.getParameter(PARAM_NAME_EMAIL));
        } else {
            throw new ServiceException("Invalid request parameters");
        }
        return user;
    }
}

