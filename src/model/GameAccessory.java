package Model;

import View.MapView;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Game accessory.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class GameAccessory {
    // player of user
    private Player player;
    // hand of the player
    private ArrayList<Card> hand;
    // towers of the player
    private ArrayList<Tower> towers;
    // in game cards of the player
    private ArrayList<Card> inGameTargets;
    // next card of the player
    private Card nextCard;
    // chosen card of the player
    private Card chosenCard;
    // elixir of the player
    private Elixir elixir;
    // number of cups in this game
    private int numberOfCups;

    /**
     * Instantiates a new Game accessory.
     *
     * @param player the player
     */
    public GameAccessory(Player player) {
        this.player = player;
        this.hand = createHand();
        this.nextCard = createNextCard();
        this.towers = Factory.createTowers(player);
        this.elixir = new Elixir(4);
        this.chosenCard = null;
        this.numberOfCups = 0;
        this.inGameTargets = new ArrayList<>();
//        this.inGameTargets.addAll(towers);
//        startTowers();
    }

    /**
     * Create hand array list.
     *
     * @return the array list
     */
    public ArrayList<Card> createHand() {
        ArrayList<Card> temp = new ArrayList<>();
        while (temp.size() != 4) {
            Card card = player.getDeck().get(new Random().nextInt(8));
            if (!temp.contains(card)) {
                temp.add(card);
            }
        }
        return temp;
    }

    /**
     * Create next card card.
     *
     * @return the card
     */
    public Card createNextCard() {
        Card temp = player.getDeck().get(new Random().nextInt(8));
        while (hand.contains(temp)) {
            temp = player.getDeck().get(new Random().nextInt(8));
        }
        return temp;
    }

    /**
     * Sets hand.
     *
     * @param hand the hand
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets hand.
     *
     * @return the hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Gets towers.
     *
     * @return the towers
     */
    public synchronized ArrayList<Tower> getTowers() {
        return towers;
    }

    /**
     * Sets towers.
     *
     * @param towers the towers
     */
    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    /**
     * Gets next card.
     *
     * @return the next card
     */
    public Card getNextCard() {
        return nextCard;
    }

    /**
     * Sets next card.
     *
     * @param nextCard the next card
     */
    public void setNextCard(Card nextCard) {
        this.nextCard = nextCard;
    }

    /**
     * Gets chosen card.
     *
     * @return the chosen card
     */
    public Card getChosenCard() {
        return chosenCard;
    }

    /**
     * Sets chosen card.
     *
     * @param chosenCard the chosen card
     */
    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }

    /**
     * Gets elixir.
     *
     * @return the elixir
     */
    public Elixir getElixir() {
        return elixir;
    }

    /**
     * Sets elixir.
     *
     * @param elixir the elixir
     */
    public void setElixir(Elixir elixir) {
        this.elixir = elixir;
    }

    /**
     * Health of towers double.
     *
     * @return the double
     */
    public double healthOfTowers() {
        double temp = 0;
        for (Tower tower : towers) {
            temp += tower.getHealth();
        }
        return temp;
    }

    /**
     * Is king in game boolean.
     *
     * @return the boolean
     */
    public boolean isKingInGame() {
        for (Tower tower : towers) {
            if (tower instanceof King) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get king king.
     *
     * @return the king
     */
    public King getKing() {
        for (Tower tower : towers) {
            if (tower instanceof King) {
                return (King) tower;
            }
        }
        return null;
    }

    /**
     * Gets number of cups.
     *
     * @return the number of cups
     */
    public int getNumberOfCups() {
        return numberOfCups;
    }

    /**
     * Increase cups.
     *
     * @param value the value
     */
    public void increaseCups(int value) {
        numberOfCups = numberOfCups + value;
    }

    /**
     * Sets number of cups.
     *
     * @param numberOfCups the number of cups
     */
    public void setNumberOfCups(int numberOfCups) {
        this.numberOfCups = numberOfCups;
    }

    /**
     * Gets in game targets.
     *
     * @return the in game targets
     */
    public synchronized ArrayList<Card> getInGameTargets() {
        return inGameTargets;
    }

    /**
     * Sets in game targets.
     *
     * @param inGameTargets the in game targets
     */
    public void setInGameTargets(ArrayList<Card> inGameTargets) {
        this.inGameTargets = inGameTargets;
    }

    /**
     * Start towers.
     */
    public void startTowers() {
        for (Tower tower : towers) {
            tower.start();
        }
    }

    /**
     * Set map on towers.
     *
     * @param map the map
     */
    public void setMapOnTowers(MapView map) {
        for (Tower tower : towers) {
            tower.setMap(map);
        }
    }

    /**
     * Remove tower.
     *
     * @param tower the tower
     */
    public synchronized void removeTower(Tower tower) {
        towers.remove(tower);
    }

    /**
     * Remove card.
     *
     * @param card the card
     */
    public synchronized void removeCard(Card card) {
        inGameTargets.remove(card);
    }
}
