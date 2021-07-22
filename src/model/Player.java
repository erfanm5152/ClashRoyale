package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Player.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Player implements Serializable {
    // reminder cards
    private ArrayList<Card> reminderCards;
    // deck cards
    private ArrayList<Card> deck;
    // user of the player
    private User user;
    // game of the player
    private Game game;
    // game accessory of player
    private transient GameAccessory gameAccessory;


    /**
     * Instantiates a new Player.
     *
     * @param user the user
     */
    public Player(User user) {
        this.user = user;
        user.setPlayer(this);
        reminderCards = Factory.createRemainingCards(this);
        deck = Factory.createBasicDeck(this);
//        towers = Factory.createTowers(this);
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

//    public int getElixir() {
//        return elixir;
//    }

//    public void setElixir(int elixir) {
//        this.elixir = elixir;
//    }

    /**
     * Gets reminder cards.
     *
     * @return the reminder cards
     */
    public ArrayList<Card> getReminderCards() {
        return reminderCards;
    }

    /**
     * Sets reminder cards.
     *
     * @param allCards the all cards
     */
    public void setReminderCards(ArrayList<Card> allCards) {
        this.reminderCards = allCards;
    }

//    public ArrayList<Tower> getTowers() {
//        return towers;
//    }

//    public void setTowers(ArrayList<Tower> towers) {
//        this.towers = towers;
//    }

    /**
     * Gets deck.
     *
     * @return the deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Sets deck.
     *
     * @param deck the deck
     */
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Gets game accessory.
     *
     * @return the game accessory
     */
    public GameAccessory getGameAccessory() {
        return gameAccessory;
    }

    /**
     * Update cards.
     */
    public void updateCards(){
        for (Card card:reminderCards) {
            card.updateLevel();
        }
        for (Card card:deck) {
            card.updateLevel();
        }
    }

    /**
     * Sets game accessory.
     *
     * @param gameAccessory the game accessory
     */
    public void setGameAccessory(GameAccessory gameAccessory) {
        this.gameAccessory = gameAccessory;
    }

    //    public Card getChosenCard() {
//        return chosenCard;
//    }

//    public void setChosenCard(Card chosenCard) {
//        this.chosenCard = chosenCard;
//    }
//
//    public Card getNextCard() {
//        return nextCard;
//    }

//    public void setNextCard(Card nextCard) {
//        this.nextCard = nextCard;
//    }
}
