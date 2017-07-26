package by.bsu.grabar.ajax.command.action;


import by.bsu.grabar.ajax.command.Command;
import by.bsu.grabar.ajax.logic.SaveGameLogic;
import by.bsu.grabar.entity.SingleGame;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import by.bsu.grabar.logic.FindUserLogic;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Save game command.
 */
public class SaveGameCommand implements Command {

    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(SaveGameCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String requestData) {
        LOG.info("Save game");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        SingleGame game = (SingleGame) session.getAttribute("game");
        try {
            SaveGameLogic.save(game, user, requestData);
            try {
                user = FindUserLogic.find(user.getEmail());
            } catch (ServiceException e) {
                LOG.error(e.getMessage());
            }
            session.setAttribute("user", user);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DAOException e) {
            LOG.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
