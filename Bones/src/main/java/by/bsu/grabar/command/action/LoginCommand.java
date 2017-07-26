package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.entity.Message;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.exception.ServiceException;
import by.bsu.grabar.logic.AccountService;
import by.bsu.grabar.logic.FindMessageLogic;
import by.bsu.grabar.logic.FindUserLogic;
import by.bsu.grabar.manager.MessageManager;
import by.bsu.grabar.manager.PageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Login command.
 */
public class LoginCommand implements Command {

    private final static Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page;
        User user;
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute(MessageManager.PARAM_LANG);

        String email = request.getParameter(MessageManager.PARAM_NAME_EMAIL);
        String password = request.getParameter(MessageManager.PARAM_NAME_PASSWORD);
        try {
            user = AccountService.authenticateByEmailAndPass(email, password);
            if (user != null) {
                session.setAttribute(MessageManager.PARAM_NAME_USER, user);
                page = PageManager.PROFILE_PAGE;
                if (user.getRole().toString().equals(MessageManager.PARAM_NAME_ADMIN.toUpperCase())) {
                    List<User> users = FindUserLogic.findAll();
                    List<Message> messages = FindMessageLogic.findAll();
                    session.setAttribute(MessageManager.PARAM_NAME_MESSAGES, messages);
                    session.setAttribute(MessageManager.PARAM_NAME_USERS, users);
                    page = PageManager.ADMIN_PAGE;
                }
            } else {
                request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(locale).getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                page = PageManager.LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(locale).getProperty(
                    MessageManager.LOGIC_EXCEPTION_ERROR_MESSAGE));
            page = PageManager.ERROR_PAGE;
        }
        return page;
    }
}
