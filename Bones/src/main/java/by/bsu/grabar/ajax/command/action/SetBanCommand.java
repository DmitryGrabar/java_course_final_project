package by.bsu.grabar.ajax.command.action;

import by.bsu.grabar.ajax.command.Command;
import by.bsu.grabar.ajax.logic.UserBanLogic;
import by.bsu.grabar.ajax.util.JsonUtil;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Set ban command.
 */
public class SetBanCommand implements Command {

    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(SetBanCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String requestData) {
        LOG.info("Set ban or unban");
        try {
            JSONObject json = JsonUtil.stringToJson(requestData);
            String email = (String) json.get("email");
            UserBanLogic.ban(email);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ServiceException e) {
            LOG.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ParseException e) {
            LOG.error(new ServiceException("Json parse exeption."));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
