package Model;

import View.MapView;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Bot extends User {
    private transient MapView mapView;
    private transient Timer timer;
    private transient Random random;
    private transient TimerTask timerTask;

    public Bot() {
        super("bot", "1234");
        timer = new Timer();
        random = new Random();
        timerTask = new TimerTask() {
            @Override
            public void run() {
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

                if (getPlayer().getGame().isFinished()){
                    stop();
                }

            }
        };
    }

    public void start() {
        timer.schedule(timerTask, 3000, 10000);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
    }

    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
}
