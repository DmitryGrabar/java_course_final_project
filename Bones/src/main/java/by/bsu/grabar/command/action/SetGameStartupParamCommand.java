package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.logic.StartupGameProp;
import by.bsu.grabar.manager.PageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Set game startup param command.
 */
public class SetGameStartupParamCommand implements Command {
    private final static Logger LOG = Logger.getLogger(SetGameStartupParamCommand.class);
    private static final String USER = "user";
    private static final String BET = "bet";
    private static final String POINTS = "points";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER);
        String bet = request.getParameter(BET);
        String points = request.getParameter(POINTS);
        StartupGameProp.setGameStartProp(user, bet, points);
        LOG.info("Setting game startup prop.");
        page = PageManager.ADMIN_PAGE;
        return page;
    }
}
