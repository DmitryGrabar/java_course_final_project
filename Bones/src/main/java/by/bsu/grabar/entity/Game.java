package by.bsu.grabar.entity;

import by.bsu.grabar.logic.StartupGameProp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The type Game.
 */
public class Game {
    private int id;
    private BigDecimal min_bet;
    private int min_points;
    private BigDecimal bet;
    private boolean win;
    private LocalDateTime date;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.min_bet = StartupGameProp.min_bet;
        this.min_points = StartupGameProp.min_points;
        this.date = LocalDateTime.now();
    }

    /**
     * Instantiates a new Game.
     *
     * @param min_bet    the min bet
     * @param min_points the min points
     */
    public Game(BigDecimal min_bet, int min_points) {
        this.min_bet = min_bet;
        this.min_points = min_points;
        this.date = LocalDateTime.now();
    }

    /**
     * Sets min bet.
     *
     * @param min_bet the min bet
     */
    public void setMin_bet(BigDecimal min_bet) {
        this.min_bet = min_bet;
    }

    /**
     * Sets min points.
     *
     * @param min_points the min points
     */
    public void setMin_points(int min_points) {
        this.min_points = min_points;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets min bet.
     *
     * @return the min bet
     */
    public BigDecimal getMin_bet() {
        return min_bet;
    }

    /**
     * Gets min points.
     *
     * @return the min points
     */
    public int getMin_points() {
        return min_points;
    }

    /**
     * Gets bet.
     *
     * @return the bet
     */
    public BigDecimal getBet() {
        return bet;
    }

    /**
     * Sets bet.
     *
     * @param bet the bet
     */
    public void setBet(BigDecimal bet) {
        this.bet = bet;
    }

    /**
     * Is win boolean.
     *
     * @return the boolean
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Sets win.
     *
     * @param win the win
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (min_points != game.min_points) return false;
        if (win != game.win) return false;
        if (min_bet != null ? !min_bet.equals(game.min_bet) : game.min_bet != null) return false;
        if (bet != null ? !bet.equals(game.bet) : game.bet != null) return false;
        return date != null ? date.equals(game.date) : game.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (min_bet != null ? min_bet.hashCode() : 0);
        result = 31 * result + min_points;
        result = 31 * result + (bet != null ? bet.hashCode() : 0);
        result = 31 * result + (win ? 1 : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
