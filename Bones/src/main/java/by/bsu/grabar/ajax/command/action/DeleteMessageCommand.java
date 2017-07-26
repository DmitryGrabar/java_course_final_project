package by.bsu.grabar.ajax.command.action;

import by.bsu.grabar.ajax.command.Command;
import by.bsu.grabar.ajax.logic.DeleteMessageLogic;
import by.bsu.grabar.entity.Message;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Delete message command.
 */
public class DeleteMessageCommand implements Command {
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(DeleteMessageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, String requestData) {
        LOG.info("Message deleted");
        HttpSession session = request.getSession();
        List<Message> messages = (List<Message>) session.getAttribute("messages");
        try {
            messages = DeleteMessageLogic.deleteMessage(requestData, messages);
            session.setAttribute("messages", messages);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ServiceException e) {
            LOG.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
