package by.bsu.grabar.ajax.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute.
     *
     * @param request     the request
     * @param response    the response
     * @param requestData the request data
     * @throws IOException the io exception
     */
    void execute(HttpServletRequest request, HttpServletResponse response, String requestData) throws IOException;
}
