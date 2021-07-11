package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<Card> allCards;
    private ArrayList<Tower> towers;
    private ArrayList<Card> deck;
    private Card chosenCard;
    private Card nextCard;
    private Game game;
    private User user;
    private int elixir;

    public Player(User user) {
        this.user = user;
        user.setPlayer(this);
        allCards = Factory.createCards(this);
        towers = Factory.createTowers(this);
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

    public int getElixir() {
        return elixir;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }
}
