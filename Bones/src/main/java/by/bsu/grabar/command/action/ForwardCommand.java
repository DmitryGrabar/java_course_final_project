package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.manager.PageManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Forward command.
 */
public class ForwardCommand implements Command {
    private static final Logger LOG = LogManager.getLogger(ForwardCommand.class);
    private static final String PARAM_LOGIN_PAGE = "toLogin";
    private static final String PARAM_PROFILE_PAGE = "toProfile";
    private static final String PARAM_PAGE = "forward";

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Forward command");
        String page;
        page = checkPage(request);
        return page;
    }

    private String checkPage(HttpServletRequest request) {
        String pageParam = request.getParameter(PARAM_PAGE);
        switch (pageParam) {
            case PARAM_LOGIN_PAGE:
                return PageManager.LOGIN_PAGE;
            case PARAM_PROFILE_PAGE:
                return PageManager.PROFILE_PAGE;
            default:
                return PageManager.LOGIN_PAGE;
        }
    }
}
