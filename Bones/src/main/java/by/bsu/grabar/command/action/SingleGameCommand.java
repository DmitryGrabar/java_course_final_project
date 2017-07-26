package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.entity.SingleGame;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.logic.StartupGameProp;
import by.bsu.grabar.manager.MessageManager;
import by.bsu.grabar.manager.PageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Single game command.
 */
public class SingleGameCommand implements Command {

    private final static Logger LOG = Logger.getLogger(SingleGameCommand.class);
    private static final String PARAM_NAME_GAME = "game";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute(MessageManager.PARAM_LANG);
        User user = (User) session.getAttribute("user");
        if (!user.getBan()) {
            StartupGameProp.getGameStartProp();
            SingleGame game = new SingleGame(StartupGameProp.min_bet, StartupGameProp.min_points, user);
            session.setAttribute(PARAM_NAME_GAME, game);
            LOG.info("New single game was created.");
            page = PageManager.PLAY_PAGE;
        } else {
            request.setAttribute(MessageManager.PARAM_ERROR_MESSAGE, MessageManager.getManagerByLocale(locale).getProperty(MessageManager.BANNED));
            page = PageManager.PROFILE_PAGE;
        }
        return page;
    }
}
