package Model;

import View.MapView;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Bot.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Bot extends User {
    // map view of the game
    private transient MapView mapView;
    // timer for timer task
    private transient Timer timer;
    // random creator
    private transient Random random;
    // timer task of the bot
    private transient TimerTask timerTask;

    /**
     * Instantiates a new Bot.
     */
    public Bot() {
        super("bot", "1234");
        timer = new Timer();
        random = new Random();
        // define timer task
        timerTask = new TimerTask() {
            @Override
            public void run() {
                // create hand and chosen card and next card
                ArrayList<Card> hand = getPlayer().getGameAccessory().getHand();
                Card chosenCard = hand.get(random.nextInt(4));
                Card nextCard = getPlayer().getGameAccessory().getNextCard();
                if (chosenCard.getCost() > getPlayer().getGameAccessory().getElixir().getElixirValue()) {
                    return;
                }
                getPlayer().getGameAccessory().getElixir().add(chosenCard.getCost() * -1);

                chosenCard.setMap(mapView);
                chosenCard.setImageView(new ImageView());
                chosenCard.setPoint2D(new Point2D(random.nextInt(260) + 20, random.nextInt(190) + 20));

                if (!(chosenCard instanceof Spell)) {
                    getPlayer().getGameAccessory().getInGameTargets().add(chosenCard);
                }

                chosenCard.start();

                getPlayer().getGame().getInMapCards().add(chosenCard);

                hand.remove(chosenCard);
                getPlayer().getDeck().remove(chosenCard);
                getPlayer().getDeck().add(Factory.createReplaceCard(chosenCard));

                hand.add(nextCard);

                getPlayer().getGameAccessory().setNextCard(getPlayer().getGameAccessory().createNextCard());

                if (getPlayer().getGame().isFinished()) {
                    stop();
                }

            }
        };
    }

    /**
     * Start.
     */
    public void start() {
        timer.schedule(timerTask, 3000, 10000);
    }

    /**
     * Stop.
     */
    public void stop() {
        timer.cancel();
        timer.purge();
    }

    /**
     * Gets map view.
     *
     * @return the map view
     */
    public MapView getMapView() {
        return mapView;
    }

    /**
     * Sets map view.
     *
     * @param mapView the map view
     */
    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public void setTimerTask(TimerTask timerTask) {
        this.timerTask = timerTask;
    }
}
