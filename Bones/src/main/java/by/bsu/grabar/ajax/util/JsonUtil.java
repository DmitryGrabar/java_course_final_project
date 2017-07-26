package by.bsu.grabar.ajax.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The type Json util.
 */
public class JsonUtil {

    private static final String COMMAND = "command";

    /**
     * Gets command.
     *
     * @param json the json
     * @return the command
     */
    public static String getCommand(JSONObject json) {
        return (String) json.get(COMMAND);
    }

    /**
     * String to json json object.
     *
     * @param data the data
     * @return the json object
     * @throws ParseException the parse exception
     */
    public static JSONObject stringToJson(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(data.trim());
    }
}
