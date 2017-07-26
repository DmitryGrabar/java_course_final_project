package by.bsu.grabar.logic;

import by.bsu.grabar.entity.User;
import by.bsu.grabar.entity.UserEnum;
import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * The type Startup game prop.
 */
public class StartupGameProp {
    public static BigDecimal min_bet;
    public static int min_points;
    private static Properties property;
    private static Reader reader = null;
    private static Writer writer = null;
    private static File file = null;
    private final static String gameStartPropPath = "C:\\Users\\dima\\IdeaProjects\\Bones\\src\\main\\resources\\prop\\game_startup.properties";
    /**
     * The constant LOG.
     */
    final static Logger LOG = Logger.getLogger(StartupGameProp.class);

    public StartupGameProp() {

    }

    public static void getGameStartProp() {
        try {
            file = new File(gameStartPropPath);
            reader = new FileReader(file);
            property = new Properties();
            property.load(reader);
            min_bet = new BigDecimal(property.getProperty("min_bet"));
            min_points = Integer.valueOf(property.getProperty("min_end_points"));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
    }

    /**
     * Sets game start prop.
     *
     * @param user             the user
     * @param minBet           the min bet
     * @param minEndGamePoints the min end game points
     */

    public static void setGameStartProp(User user, String minBet, String minEndGamePoints) {
        if (user.getRole().equals(UserEnum.ADMIN)) {
            try {
                file = new File(gameStartPropPath);
                writer = new FileWriter(file);
                property = new Properties();
                property.setProperty("min_bet", minBet);
                property.setProperty("min_end_points", minEndGamePoints);
                property.store(writer, "comment");
            } catch (IOException e) {
                LOG.error(e.getMessage());
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
    }
}
