package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {
    private String name;
    private String password;
    private int xp;
    private int numberOfCups;
    private Level level;
    private Player player;
    private ArrayList<Game> gameHistory;

    public User(String name,String password) {

        this.name = name;
        this.password = password;
        this.xp = 0;
        this.numberOfCups = 0;
        this.level = Level.getLevelByXp(xp);
        this.gameHistory = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getXp() {
        return xp;
    }

    public int getNumberOfCups() {
        return numberOfCups;
    }

    public Level getLevel() {
        return level;
    }

    public ArrayList<Game> getGameHistory() {
        return gameHistory;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void increaseXp(int value){
        xp = xp+value;
    }

    public void increaseCups(int value){
        numberOfCups = numberOfCups+value;
    }

    public void updateLevel(){
        this.level = Level.getLevelByXp(xp);
        player.updateCards();
    }

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

}
