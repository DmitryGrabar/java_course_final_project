package by.bsu.grabar.hibernate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "game", schema = "mydb", catalog = "")
public class GameEntity {
    private int gameId;
    private int minBet;
    private int minPoints;
    private Timestamp date;
    private Integer bet;

    @Id
    @Column(name = "game_id", nullable = false)
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name = "min_bet", nullable = false, precision = 0)
    public int getMinBet() {
        return minBet;
    }

    public void setMinBet(int minBet) {
        this.minBet = minBet;
    }

    @Basic
    @Column(name = "min_points", nullable = false)
    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "bet", nullable = true, precision = 0)
    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEntity that = (GameEntity) o;

        if (gameId != that.gameId) return false;
        if (minBet != that.minBet) return false;
        if (minPoints != that.minPoints) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (bet != null ? !bet.equals(that.bet) : that.bet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gameId;
        result = 31 * result + minBet;
        result = 31 * result + minPoints;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (bet != null ? bet.hashCode() : 0);
        return result;
    }
}
