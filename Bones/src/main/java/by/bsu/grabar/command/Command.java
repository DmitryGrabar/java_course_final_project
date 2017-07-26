package by.bsu.grabar.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute string.
     *
     * @param request the request
     * @return the string
     */
    String execute(HttpServletRequest request);
}
