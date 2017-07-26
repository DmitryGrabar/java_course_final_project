package by.bsu.grabar.ajax.controller;

import by.bsu.grabar.ajax.command.Command;
import by.bsu.grabar.ajax.command.RequestHelper;
import by.bsu.grabar.ajax.util.AjaxServletUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.bsu.grabar.ajax.util.AjaxServletUtil.APPLICATION_JSON;
import static by.bsu.grabar.ajax.util.AjaxServletUtil.UTF_8;

/**
 * The type Ajax controller.
 */
@WebServlet("/ajaxController")
public class AjaxController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AjaxController.class);

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("GET method");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("POST method");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(APPLICATION_JSON);
        resp.setCharacterEncoding(UTF_8);
        String requestData = AjaxServletUtil.getRequestBody(req);
        Command command = RequestHelper.getCommand(requestData);
        command.execute(req, resp, requestData);
    }
}
