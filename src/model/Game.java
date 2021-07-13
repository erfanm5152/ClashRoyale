package Model;

import java.io.Serializable;

public class Game implements Serializable {
    private User user1;
    private User user2;
    private SharedData sharedData;
    private String[][] map;

    public Game() {
        sharedData = SharedData.getInstance();
        user1 = sharedData.getUser();
//        user2 = bot;
        map=FileUtils.readMap();
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String[][] getMap() {
        return map;
    }

    public void setMap(String[][] map) {
        this.map = map;
    }
}
