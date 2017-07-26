package by.bsu.grabar.ajax.logic;

import by.bsu.grabar.ajax.util.JsonUtil;
import by.bsu.grabar.dao.inml.GameDao;
import by.bsu.grabar.dao.inml.UserDao;
import by.bsu.grabar.entity.SingleGame;
import by.bsu.grabar.entity.User;
import by.bsu.grabar.error.DAOException;
import by.bsu.grabar.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.math.BigDecimal;

/**
 * The type Save game logic.
 */
public class SaveGameLogic {
    /**
     * The constant LOG.
     */
    public final static Logger LOG = LogManager.getLogger(SaveGameLogic.class);

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
        BigDecimal bet = new BigDecimal ((String)json.get("bet"));
        user.setRating(bet.doubleValue() / 10);
        game.setBet(bet);
        game.setWin(win);
        GameDao.getInstance().add(game);
        if (win) {
            UserDao.getInstance().addWin(user, bet);
        } else {
            UserDao.getInstance().addLose(user, bet);
        }
    }
}
