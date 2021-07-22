package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The type User.
 * To store user information.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class User implements Serializable {
    // name of the user
    private String name;
    // password of the user
    private String password;
    // xp of the user
    private int xp;
    // number of cups of the user
    private int numberOfCups;
    // level of the user
    private Level level;
    // player of the user
    private Player player;
    // history of games of the user
    private ArrayList<Game> gameHistory;

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     */
    public User(String name, String password) {

        this.name = name;
        this.password = password;
        this.xp = 0;
        this.numberOfCups = 0;
        this.level = Level.getLevelByXp(xp);
        this.gameHistory = new ArrayList<>();

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets xp.
     *
     * @return the xp
     */
    public int getXp() {
        return xp;
    }

    /**
     * Gets number of cups.
     *
     * @return the number of cups
     */
    public int getNumberOfCups() {
        return numberOfCups;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Gets game history.
     *
     * @return the game history
     */
    public ArrayList<Game> getGameHistory() {
        return gameHistory;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Increase xp.
     *
     * @param value the value
     */
    public void increaseXp(int value) {
        xp = xp + value;
    }

    /**
     * Increase cups.
     *
     * @param value the value
     */
    public void increaseCups(int value) {
        numberOfCups = numberOfCups + value;
    }

    /**
     * Update level.
     */
    public void updateLevel() {
        this.level = Level.getLevelByXp(xp);
        player.updateCards();
    }

    /**
     * check equality of two user
     *
     * @param o second user
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * convert user to string
     *
     * @return string of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", xp=" + xp +
                ", numberOfCups=" + numberOfCups +
                ", level=" + level +
                '}';
    }

    /**
     * Number of wins .
     *
     * @return the number of wins.
     */
    public int numberOfWins() {
        int temp = 0;
        for (Game game : getGameHistory()) {
            if (game.getWinner().equals(this)) {
                temp++;
            }
        }
        return temp;
    }

    /**
     * Number of loses .
     *
     * @return the number of loses
     */
    public int numberOfLose() {
        return getGameHistory().size() - numberOfWins();
    }

}
