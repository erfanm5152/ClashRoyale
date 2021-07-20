package Model;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class TimerMe {
    private Label label;
    private Game game ;
    private int timeInt;
    private Timer timer;
    private TimerTask timerTask;
    private double increasePerSecond;
    private boolean finished;

    public TimerMe(Label label,Game game) {
        this.label = label;
        this.game =game;
        this.increasePerSecond = 0.5;
        this.timeInt=180;
        this.label.setText("3:00");
        this.finished = false;
        this.timer = new Timer();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timeInt-=1;
                        label.setText(timeInt/60 +" : "+timeInt%60);
                        game.getUser1().getPlayer().getGameAccessory().getElixir().add(increasePerSecond);
                        if (timeInt<60){
                            increasePerSecond = 1;
                        }
                        if (game.isFinished()||timeInt==0){
                            finished = true;
                            timer.cancel();
                            timer.purge();
                        }
                    }
                });
            }
        };
    }

    public void start(){
        timer.schedule(timerTask,0,1000);
    }


    public int getTimeInt() {
        return timeInt;
    }

    public void setTimeInt(int timeInt) {
        this.timeInt = timeInt;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public double getIncreasePerSecond() {
        return increasePerSecond;
    }

    public void setIncreasePerSecond(double increasePerSecond) {
        this.increasePerSecond = increasePerSecond;
    }

    public synchronized boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
