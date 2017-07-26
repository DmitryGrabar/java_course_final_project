package by.bsu.grabar.entity;

import java.math.BigDecimal;

/**
 * The type Single game.
 */
public class SingleGame extends Game {
    private User user;

    /**
     * Instantiates a new Single game.
     *
     * @param min_bet    the min bet
     * @param min_points the min points
     * @param user       the user
     */
    public SingleGame(BigDecimal min_bet, int min_points, User user) {
        super(min_bet, min_points);
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SingleGame that = (SingleGame) o;

        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
