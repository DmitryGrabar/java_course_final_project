package by.bsu.grabar.entity;

import java.math.BigDecimal;

/**
 * The type User.
 */
public class User {
    private int id;
    private double rating;
    private String login;
    private String password;
    private String email;
    private boolean ban;
    private BigDecimal money;
    private UserEnum role;
    private String imgPath;
    private int allGames;
    private int winGames;
    private final BigDecimal START_MONEY = new BigDecimal(100);
    private final double START_RATING = 10.0;
    private final String gameStartPropPath = "src/main/resources/prop/game_startup.properties";

    /**
     * Instantiates a new User.
     */
    public User() {
        this.ban = false;
        this.money = START_MONEY;
        this.role = UserEnum.USER;
        this.rating = START_RATING;
        this.imgPath = null;
        allGames = 0;
        winGames = 0;
    }

    /**
     * Instantiates a new User.
     *
     * @param login    the login
     * @param password the password
     * @param email    the email
     * @param ban      the ban
     * @param money    the money
     * @param role     the role
     * @param imgPath  the img path
     */
    public User(String login, String password, String email, boolean ban, BigDecimal money, UserEnum role, String imgPath) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.ban = ban;
        this.money = money;
        this.role = role;
        this.imgPath = imgPath;
    }

    /**
     * Gets all games.
     *
     * @return the all games
     */
    public int getAllGames() {
        return allGames;
    }

    /**
     * Sets all games.
     *
     * @param allGames the all games
     */
    public void setAllGames(int allGames) {
        this.allGames = allGames;
    }

    /**
     * Gets win games.
     *
     * @return the win games
     */
    public int getWinGames() {
        return winGames;
    }

    /**
     * Sets win games.
     *
     * @param winGames the win games
     */
    public void setWinGames(int winGames) {
        this.winGames = winGames;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public UserEnum getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(UserEnum role) {
        this.role = role;
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
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets img path.
     *
     * @return the img path
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Sets img path.
     *
     * @param imgPath the img path
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets ban.
     *
     * @return the ban
     */
    public boolean getBan() {
        return ban;
    }

    /**
     * Sets ban.
     *
     * @param ban the ban
     */
    public void setBan(boolean ban) {
        this.ban = ban;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "{\"email\":\"" + email +
                "\", \"money\":\"" + money +
                "\", \"login\":\"" + login +
                "\", \"ban\":\"" + ban +
                "\", \"rating\":\"" + rating +
                "\", \"rating\":\"" + password +
                "\", \"rating\":\"" + role +
                "\", \"rating\":\"" + imgPath +
                "\", \"rating\":\"" + allGames +
                "\", \"rating\":\"" + winGames +
                "\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (Double.compare(user.rating, rating) != 0) return false;
        if (ban != user.ban) return false;
        if (allGames != user.allGames) return false;
        if (winGames != user.winGames) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (money != null ? !money.equals(user.money) : user.money != null) return false;
        if (role != user.role) return false;
        if (imgPath != null ? !imgPath.equals(user.imgPath) : user.imgPath != null) return false;
        if (START_MONEY != null ? !START_MONEY.equals(user.START_MONEY) : user.START_MONEY != null) return false;
        return gameStartPropPath != null ? gameStartPropPath.equals(user.gameStartPropPath) : user.gameStartPropPath == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ban ? 1 : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + allGames;
        result = 31 * result + winGames;
        result = 31 * result + (START_MONEY != null ? START_MONEY.hashCode() : 0);
        result = 31 * result + (gameStartPropPath != null ? gameStartPropPath.hashCode() : 0);
        return result;
    }
}
