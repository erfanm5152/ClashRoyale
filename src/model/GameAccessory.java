package Model;

import java.util.ArrayList;
import java.util.Random;

public class GameAccessory {
    private Player player;
    private ArrayList<Card> hand ;
    private ArrayList<Tower> towers;
    private Card nextCard;
    private Card chosenCard;

    public GameAccessory(Player player){
        this.player = player;
        this.hand = createHand();
        this.nextCard = createNextCard();
        this.towers = Factory.createTowers(player);
        this.chosenCard = null;
    }

    public ArrayList<Card> createHand(){
        ArrayList<Card> temp = new ArrayList<>();
        while (temp.size()!=4){
            Card card = player.getDeck().get(new Random().nextInt(8));
            if (!temp.contains(card)) {
                temp.add(card);
            }
        }
        return temp;
    }

    public Card createNextCard(){
        Card temp = player.getDeck().get(new Random().nextInt(8));
        while (hand.contains(temp)){
            temp = player.getDeck().get(new Random().nextInt(8));
        }
        return temp;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    public Card getNextCard() {
        return nextCard;
    }

    public void setNextCard(Card nextCard) {
        this.nextCard = nextCard;
    }

    public Card getChosenCard() {
        return chosenCard;
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }
}
