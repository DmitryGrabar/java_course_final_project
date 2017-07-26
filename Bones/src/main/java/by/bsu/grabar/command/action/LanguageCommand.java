package by.bsu.grabar.command.action;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.manager.PageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Language command.
 */
public class LanguageCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);
    private static final String PARAM_LANG = "language";

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Language command");
        String page;
        HttpSession session = request.getSession(true);
        String lang = request.getParameter(PARAM_LANG);
        LOG.info("Add to session language parameter.");
        session.setAttribute(PARAM_LANG, lang);
        page = PageManager.LOGIN_PAGE;
        return page;
    }
}
