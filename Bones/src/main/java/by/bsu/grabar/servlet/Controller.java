package by.bsu.grabar.servlet;

import by.bsu.grabar.command.Command;
import by.bsu.grabar.command.action.CommandManager;
import by.bsu.grabar.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 */
@WebServlet(name = "controller", urlPatterns = {"/controller"})
@MultipartConfig
public class Controller extends HttpServlet {

    /**
     * The constant LOG.
     */
    final static Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("command");
        Command command = CommandManager.takeCommand(action);
        String page = command.execute(request);
        LOG.info("Take command: " + action);
        if (request.getAttribute("type") == "redirect") {
            response.sendRedirect(page);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyConnections();
        super.destroy();
    }
}
