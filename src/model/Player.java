package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<Card> reminderCards;
//    private ArrayList<Tower> towers;
    private ArrayList<Card> deck;
//    private Card chosenCard;
//    private Card nextCard;
    private User user;
    private Game game;
    private transient GameAccessory gameAccessory;
//    private int elixir;


    public Player(User user) {
        this.user = user;
        user.setPlayer(this);
        reminderCards = Factory.createRemainingCards(this);
        deck = Factory.createBasicDeck(this);
//        towers = Factory.createTowers(this);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public int getElixir() {
//        return elixir;
//    }

//    public void setElixir(int elixir) {
//        this.elixir = elixir;
//    }

    public ArrayList<Card> getReminderCards() {
        return reminderCards;
    }

    public void setReminderCards(ArrayList<Card> allCards) {
        this.reminderCards = allCards;
    }

//    public ArrayList<Tower> getTowers() {
//        return towers;
//    }

//    public void setTowers(ArrayList<Tower> towers) {
//        this.towers = towers;
//    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public GameAccessory getGameAccessory() {
        return gameAccessory;
    }

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
