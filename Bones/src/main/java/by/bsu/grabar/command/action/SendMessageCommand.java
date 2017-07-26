package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.exception.ServiceException;
import by.bsu.grabar.manager.MessageManager;
import by.bsu.grabar.manager.PageManager;
import by.bsu.grabar.logic.SendMessageToAdmin;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Send message command.
 */
public class SendMessageCommand implements Command {
    private final static Logger LOG = Logger.getLogger(SendMessageCommand.class);
    private static final String TEXT = "text";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(MessageManager.PARAM_NAME_USER);
        String text = request.getParameter(TEXT);
        try {
            SendMessageToAdmin.send(user, text);
            page = PageManager.PROFILE_PAGE;
        } catch (ServiceException e) {
            LOG.error("Something has gone wrong, redirect to error page.", e);
            request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.MESSAGE_WASNT_SENT);
            page = PageManager.ERROR_PAGE;
        }
        return page;
    }
}
