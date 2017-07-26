package by.bsu.grabar.ajax.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * The type Ajax servlet util.
 */
public class AjaxServletUtil {
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(AjaxServletUtil.class);
    /**
     * The constant APPLICATION_JSON.
     */
    public static final String APPLICATION_JSON = "application/json";
    /**
     * The constant UTF_8.
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * Gets request body.
     *
     * @param request the request
     * @return the request body
     * @throws IOException the io exception
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
