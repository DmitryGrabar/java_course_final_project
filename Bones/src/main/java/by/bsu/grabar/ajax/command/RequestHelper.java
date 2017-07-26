package by.bsu.grabar.ajax.command;

import by.bsu.grabar.ajax.command.action.DeleteMessageCommand;
import by.bsu.grabar.ajax.command.action.SaveGameCommand;
import by.bsu.grabar.ajax.command.action.SetBanCommand;
import by.bsu.grabar.ajax.util.JsonUtil;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

/**
 * The type Request helper.
 */
public class RequestHelper {
    private static HashMap <String,Command> commands = new HashMap<String,Command>();
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(RequestHelper.class);

    static{
        commands.put("saveGame", new SaveGameCommand());
        commands.put("setBan", new SetBanCommand());
        commands.put("delete_message", new DeleteMessageCommand());
    }

    /**
     * Get command command.
     *
     * @param requestData the request data
     * @return the command
     */
    public static Command getCommand(String requestData) {
        JSONObject json = null;
        try {
            json = JsonUtil.stringToJson(requestData);
        } catch (ParseException e){
            LOG.error(new ServiceException("Command not found"));
        }
        String action = JsonUtil.getCommand(json);
        Command command = commands.get(action);
        LOG.info(action);
        return command;
    }

}

