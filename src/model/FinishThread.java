package Model;

import Controller.MenuController;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class FinishThread extends TimerTask {

    private Game game;
    private Timer timer;

    public FinishThread(Game game) {
        this.game = game;
        this.timer = new Timer();
    }

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
//                    game.winnerInFinishTime();
                }
                if (!game.getUser1().getPlayer().getGameAccessory().isKingInGame()){
                    game.setFinished(true);
//                    game.setWinner(game.getUser2());
                }
//                if (!game.getUser2().getPlayer().getGameAccessory().isKingInGame()){
//                    game.setFinished(true);
//                    game.setWinner(game.getUser1());
//                }
                if (game.isFinished()){
                    game.getUser1().getGameHistory().add(game);
//                    game.getUser2().getGameHistory().add(game);
                    MenuController.changeToMenu(game.getTimerMe().getLabel());
                    timer.cancel();
                    timer.purge();
                }
            }
        });
    }
}
