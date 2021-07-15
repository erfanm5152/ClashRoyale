package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private User user1;
    private User user2;
    private User winner;
    private boolean isFinished;
    private SharedData sharedData;
    private String[][] map;
    // todo ye thread zade shavad baraye inke joon in card ha chekc shavad va dar sorat mordan pak shavand.
    private ArrayList<Card> inMapCards;
    private TimerMe timerMe;

    public Game() {
        sharedData = SharedData.getInstance();
        user1 = sharedData.getUser();
//        user2 = bot;
        isFinished = false;
        map=FileUtils.readMap();
        inMapCards = new ArrayList<>();
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

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public SharedData getSharedData() {
        return sharedData;
    }

    public void setSharedData(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public ArrayList<Card> getInMapCards() {
        return inMapCards;
    }

    public void setInMapCards(ArrayList<Card> inMapCards) {
        this.inMapCards = inMapCards;
    }

    public TimerMe getTimerMe() {
        return timerMe;
    }

    public void setTimerMe(TimerMe timerMe) {
        this.timerMe = timerMe;
    }

    public void winnerInFinishTime(){
        if (user1.getPlayer().getGameAccessory().healthOfTowers()>=user2.getPlayer().getGameAccessory().healthOfTowers()){
            winner =  user1;
        }else{
            winner = user2;
        }
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }
}
