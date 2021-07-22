package Model;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Timer me.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class TimerMe {
    // label of timer
    private Label label;
    // game of timer
    private Game game ;
    // value of time (second)
    private int timeInt;
    // timer of task
    private Timer timer;
    // timer task for this timer
    private TimerTask timerTask;
    // increase of elixir per second
    private double increasePerSecond;
    // is timer finished
    private boolean finished;

    /**
     * Instantiates a new Timer me.
     *
     * @param label the label
     * @param game  the game
     */
    public TimerMe(Label label,Game game) {
        this.label = label;
        this.game =game;
        this.increasePerSecond = 0.5;
        this.timeInt=180;
        this.label.setText("3:00");
        this.finished = false;
        this.timer = new Timer();
        // define timer task
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timeInt-=1;
                        label.setText(timeInt/60 +" : "+timeInt%60);
                        game.getUser1().getPlayer().getGameAccessory().getElixir().add(increasePerSecond);
                        game.getUser2().getPlayer().getGameAccessory().getElixir().add(increasePerSecond);
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

    /**
     * Start.
     */
    public void start(){
        timer.schedule(timerTask,0,1000);
    }


    /**
     * Gets time int.
     *
     * @return the time int
     */
    public int getTimeInt() {
        return timeInt;
    }

    /**
     * Sets time int.
     *
     * @param timeInt the time int
     */
    public void setTimeInt(int timeInt) {
        this.timeInt = timeInt;
    }

    /**
     * Gets timer.
     *
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Sets timer.
     *
     * @param timer the timer
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Gets timer task.
     *
     * @return the timer task
     */
    public TimerTask getTimerTask() {
        return timerTask;
    }

    /**
     * Sets timer task.
     *
     * @param timerTask the timer task
     */
    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets label.
     *
     * @param label the label
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * Gets increase per second.
     *
     * @return the increase per second
     */
    public double getIncreasePerSecond() {
        return increasePerSecond;
    }

    /**
     * Sets increase per second.
     *
     * @param increasePerSecond the increase per second
     */
    public void setIncreasePerSecond(double increasePerSecond) {
        this.increasePerSecond = increasePerSecond;
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public synchronized boolean isFinished() {
        return finished;
    }

    /**
     * Sets finished.
     *
     * @param finished the finished
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
