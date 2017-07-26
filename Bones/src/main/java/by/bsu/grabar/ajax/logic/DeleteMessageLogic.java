package by.bsu.grabar.ajax.logic;

import by.bsu.grabar.ajax.command.action.DeleteMessageCommand;
import by.bsu.grabar.ajax.util.JsonUtil;
import by.bsu.grabar.dao.inml.GameDao;
import by.bsu.grabar.dao.inml.MessageDao;
import by.bsu.grabar.dao.inml.UserDao;
import by.bsu.grabar.entity.Message;
import by.bsu.grabar.entity.SingleGame;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Delete message logic.
 */
public class DeleteMessageLogic {

    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(DeleteMessageCommand.class);

    /**
     * Save.
     *
     * @param game the game
     * @param user the user
     * @param data the data
     * @throws DAOException the dao exception
     */
    public static void save(SingleGame game, User user, String data) throws DAOException {
        JSONObject json = null;
        try {
            json = JsonUtil.stringToJson(data);
        } catch (ParseException e) {
            LOG.error(new ServiceException(e.getMessage()));
        }
        boolean win = Boolean.parseBoolean((String) json.get("win"));
        BigDecimal bet = new BigDecimal((String) json.get("bet"));
        game.setBet(bet);
        game.setWin(win);
        GameDao.getInstance().add(game);
        if (win) {
            UserDao.getInstance().addWin(user, bet);
        } else {
            UserDao.getInstance().addLose(user, bet);
        }
    }

    /**
     * Delete message list.
     *
     * @param data     the data
     * @param messages the messages
     * @return the list
     * @throws ServiceException the service exception
     */
    public static List<Message> deleteMessage(String data, List<Message> messages) throws ServiceException {
        JSONObject json = null;
        try {
            try {
                json = JsonUtil.stringToJson(data);
            } catch (ParseException e) {
                LOG.error(new ServiceException(e.getMessage()));
            }
            String text = (String) json.get("text");
            Integer userId = Integer.parseInt((String) json.get("userId"));
            MessageDao.getInstance().deleteMessage(text, userId);
            for (Message mes : messages) {
                if (mes.getText().equals(text) && mes.getUserId() == userId) {
                    messages.remove(mes);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        return messages;
    }
}
