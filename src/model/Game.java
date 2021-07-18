package Model;

import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private User user1;
    private User user2;
    private User winner;
    private User loser;
    private boolean isFinished;
    private transient SharedData sharedData;
    private String[][] map;
    // todo ye thread zade shavad baraye inke joon in card ha chekc shavad va dar sorat mordan pak shavand.
    private ArrayList<Card> inMapCards;
    private transient Point2D rightBridgeUser1;
    private transient Point2D leftBridgeUser1;
    private transient Point2D rightBridgeUser2;
    private transient Point2D leftBridgeUser2;
    private transient TimerMe timerMe;

    public Game() {
        sharedData = SharedData.getInstance();

        user1 = sharedData.getUser();
        user2 = new Bot();
        user2.setPlayer(new Player(user2));

        isFinished = false;
        map=FileUtils.readMap();
        inMapCards = new ArrayList<>();
        this.rightBridgeUser1 = new Point2D(255, 287);
        this.rightBridgeUser2 = new Point2D(255, 254);
        this.leftBridgeUser1 = new Point2D(63, 287);
        this.leftBridgeUser2 = new Point2D(63,254 );
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
        int numberOfCupsUser1 = user1.getPlayer().getGameAccessory().getNumberOfCups();
        int numberOfCupsUser2 = user2.getPlayer().getGameAccessory().getNumberOfCups();
        if (numberOfCupsUser1!=numberOfCupsUser2){
            winner= numberOfCupsUser1>numberOfCupsUser2 ?user1 :user2;
        }else {
            if (user1.getPlayer().getGameAccessory().healthOfTowers() > user2.getPlayer().getGameAccessory().healthOfTowers()) {
                winner = user1;
            } else if (user1.getPlayer().getGameAccessory().healthOfTowers() == user2.getPlayer().getGameAccessory().healthOfTowers()) {
                winner = user1;
            } else if (user1.getPlayer().getGameAccessory().healthOfTowers() < user2.getPlayer().getGameAccessory().healthOfTowers()) {
                winner = user2;
            }
        }
        loser = user1==winner? user2: user1 ;
    }

    public User getOpponent(User selfUser){
        return selfUser==user1?user2:user1;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getLoser() {
        return loser;
    }

    public void setLoser(User loser) {
        this.loser = loser;
    }

    public Point2D getRightBridgeUser1() {
        return rightBridgeUser1;
    }

    public void setRightBridgeUser1(Point2D rightBridgeUser1) {
        this.rightBridgeUser1 = rightBridgeUser1;
    }

    public Point2D getLeftBridgeUser1() {
        return leftBridgeUser1;
    }

    public void setLeftBridgeUser1(Point2D leftBridgeUser1) {
        this.leftBridgeUser1 = leftBridgeUser1;
    }

    public Point2D getRightBridgeUser2() {
        return rightBridgeUser2;
    }

    public void setRightBridgeUser2(Point2D rightBridgeUser2) {
        this.rightBridgeUser2 = rightBridgeUser2;
    }

    public Point2D getLeftBridgeUser2() {
        return leftBridgeUser2;
    }

    public void setLeftBridgeUser2(Point2D leftBridgeUser2) {
        this.leftBridgeUser2 = leftBridgeUser2;
    }

    public boolean isEmptyCell(Point2D point2D , Card card){
        ArrayList<Card> cards =new ArrayList<>();
        for (Card temp:getUser1().getPlayer().getGameAccessory().getInGameTargets()) {
            if (!(temp instanceof Spell)){
                cards.add(temp);
            }
        }
        for (Card temp:getUser2().getPlayer().getGameAccessory().getInGameTargets()) {
            if (!(temp instanceof Spell)){
                cards.add(temp);
            }
        }
        for (Card temp:cards) {
            if (temp == card){ continue; }
            if (temp.getPoint2D().equals(point2D)||temp.getPoint2D().distance(point2D)<2){
                return false;
            }
        }

        return true;
    }

}
