package Model;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

/**
 * The type Advanced bot.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class AdvancedBot extends Bot {
    private int secondInGame;
    private int period;
    /**
     * Instantiates a new Advanced bot.
     */
    public AdvancedBot() {
        super();
        secondInGame = 0;
        period = 5000;
        setTimerTask(new TimerTask() {
            @Override
            public void run() {
                if (secondInGame % period == 0) {
                    if (secondInGame >=120000){period = 4000;}
                    ArrayList<Card> hand = getPlayer().getGameAccessory().getHand();
                    Card chosenCard = hand.get(getRandom().nextInt(4));
                    Card nextCard = getPlayer().getGameAccessory().getNextCard();
                    if (chosenCard.getCost() > getPlayer().getGameAccessory().getElixir().getElixirValue()) {
                        return;
                    }
                    getPlayer().getGameAccessory().getElixir().add(chosenCard.getCost() * -1);

                    chosenCard.setMap(getMapView());
                    chosenCard.setImageView(new ImageView());
                    chosenCard.setPoint2D(new Point2D(getRandom().nextInt(260) + 20, getRandom().nextInt(190) + 20));
                    makeSmart(chosenCard);

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
                secondInGame+=100;
            }
        });
    }

    /**
     * Spell move card.
     *
     * @param spellCard the spell card
     */
    public void spellMoveCard(Card spellCard) {
        ArrayList<Tower> opponentTowers = getPlayer().getGame().getOpponent(getPlayer().getUser())
                .getPlayer().getGameAccessory().getTowers();
        spellCard.setPoint2D(opponentTowers.get(getRandom().nextInt(opponentTowers.size())).getPoint2D());
    }

    /**
     * Is large number of cards in game boolean.
     *
     * @return the boolean
     */
    public boolean isLargeNumberOfCardsInGame() {
        return getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().getInGameTargets()
                .size() >= 4;
    }

    /**
     * Put near opponent.
     *
     * @param chosenCard the chosen card
     */
    public void putNearOpponent(Card chosenCard) {
        Point2D temp = new Point2D(0, 0);
        ArrayList<Card> opponentCards = getPlayer().getGame().getOpponent(getPlayer().getUser())
                .getPlayer().getGameAccessory().getInGameTargets();
        for (Card card : opponentCards) {
            temp = temp.add(card.getPoint2D());
        }
        temp = new Point2D(temp.getX() / opponentCards.size(), temp.getY() / opponentCards.size());
        if (getPlayer().getGame().checkPoint(temp, chosenCard)) {
            chosenCard.setPoint2D(temp);
        } else {
            chosenCard.setPoint2D(new Point2D(temp.getX(), getRandom().nextInt(100) + 100));
        }
    }

    /**
     * Is towers destroyed boolean.
     *
     * @return the boolean
     */
    public boolean isTowersDestroyed(){
        ArrayList<Tower> opponentTowers = getPlayer().getGame().getOpponent(getPlayer().getUser())
                .getPlayer().getGameAccessory().getTowers();
        return opponentTowers.size() <3;
    }

    /**
     * Save king.
     *
     * @param chosenCard the chosen card
     */
    public void saveKing(Card chosenCard){
            Random random = new Random();
            int x = random.nextInt(2) == 0 ? -1 : 1;
            int y = random.nextInt(2) == 0 ? -1 : 1;
            chosenCard.setPoint2D(new Point2D(180,60).subtract(x * random.nextInt(5), y * random.nextInt(5)));
    }

    /**
     * Make smart.
     *
     * @param chosenCard the chosen card
     */
    public void makeSmart(Card chosenCard) {
        if (chosenCard instanceof Spell) {
            spellMoveCard(chosenCard);
        }
        if (chosenCard instanceof Soldier) {
            if (isTowersDestroyed() ){
                saveKing(chosenCard);
            }
            if (isLargeNumberOfCardsInGame()){
                putNearOpponent(chosenCard);
            }
        }
    }

    @Override
    public void start() {
        getTimer().schedule(getTimerTask(),2000,100);
    }
}
