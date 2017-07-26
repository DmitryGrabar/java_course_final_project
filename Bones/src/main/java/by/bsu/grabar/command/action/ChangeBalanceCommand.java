package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.exception.ServiceException;
import by.bsu.grabar.logic.ReplenishBalance;
import by.bsu.grabar.manager.MessageManager;
import by.bsu.grabar.manager.PageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Change balance command.
 */
public class ChangeBalanceCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ChangeBalanceCommand.class);
    private static final String USER = "user";
    private static final String LANG = "lang";
    private static final String MONEY = "money";
    private String COMMAND_NAME = "";

    /**
     * Instantiates a new Change balance command.
     *
     * @param command the command
     */
    public ChangeBalanceCommand(String command) {
        this.COMMAND_NAME = command;
    }

    @Override
    public String execute(HttpServletRequest request){
        String page = null;
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute(LANG);
        User user = (User) session.getAttribute(USER);
        String money = request.getParameter(MONEY);
        LOG.info("Change balance command");
        try {
            ReplenishBalance.replenishBalance(user, money, locale, COMMAND_NAME);
            page = PageManager.PROFILE_PAGE;
        }
        catch (ServiceException e) {
            LOG.error("Replenish exception", e);
            request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(locale).getProperty(MessageManager.INCORRECT_SUM));
            page = PageManager.ERROR_PAGE;
        }
        return page;
    }
}

