package Model;

import Controller.FinishController;
import Controller.MenuController;
import javafx.application.Platform;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Finish thread.
 *
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class FinishThread extends TimerTask {
    // main game
    private Game game;
    // timer of this task
    private Timer timer;

    /**
     * Instantiates a new Finish thread.
     *
     * @param game the game
     */
    public FinishThread(Game game) {
        this.game = game;
        this.timer = new Timer();
    }

    /**
     * Start timer.
     */
    public void start(){
        timer.schedule(this,0,1000);
    }

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (game.getTimerMe().isFinished()){
                    game.setFinished(true);
                    game.winnerInFinishTime();
                }
                if (!game.getUser1().getPlayer().getGameAccessory().isKingInGame()){
                    game.setFinished(true);
                    game.setWinner(game.getUser2());
                    game.setLoser(game.getUser1());
                }
                if (!game.getUser2().getPlayer().getGameAccessory().isKingInGame()){
                    game.setFinished(true);
                    game.setWinner(game.getUser1());
                    game.setLoser(game.getUser2());
                }
                if (game.isFinished()){
                    game.getUser1().getGameHistory().add(game);
                    game.getUser2().getGameHistory().add(game);
                    game.getWinner().increaseXp(200);
                    game.getLoser().increaseXp(70);
                    game.getWinner().increaseCups(game.getWinner().getPlayer().getGameAccessory().getNumberOfCups());
                    game.getLoser().increaseCups(game.getLoser().getPlayer().getGameAccessory().getNumberOfCups());
                    game.getUser1().updateLevel();
                    game.getUser2().updateLevel();
//                    stopTowers();
//                    stopCards();
//                    MenuController.changeToMenu(game.getTimerMe().getLabel());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    FinishController.finishView(game.getTimerMe().getLabel());
                    timer.cancel();
                    timer.purge();
                }
            }
        });
    }

    /**
     * Stop towers.
     */
    public void stopTowers(){
        stopTowersOfUser(game.getUser1());
        stopTowersOfUser(game.getUser2());
    }

    /**
     * Stop towers of user.
     *
     * @param user the user
     */
    public void stopTowersOfUser(User user){
        Iterator<Tower> towerIterator = user.getPlayer().getGameAccessory().getTowers().iterator();
        while (towerIterator.hasNext()){
            Tower temp = towerIterator.next();
            temp.stop();
        }
    }

    /**
     * Stop cards.
     */
    public void stopCards(){
        stopCardsOfUser(game.getUser1());
        stopCardsOfUser(game.getUser2());
    }

    /**
     * Stop cards of user.
     *
     * @param user the user
     */
    public synchronized void stopCardsOfUser(User user){
        Iterator<Card> cardIterator = user.getPlayer().getGameAccessory().getInGameTargets().iterator();
        while (cardIterator.hasNext()){
            Card temp = cardIterator.next();
            temp.stop();
        }
    }
}
