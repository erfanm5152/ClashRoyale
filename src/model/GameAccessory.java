package Model;

import java.util.ArrayList;
import java.util.Random;

public class GameAccessory {
    private Player player;
    private ArrayList<Card> hand ;
    private ArrayList<Tower> towers;
    private ArrayList<Vulnerable> inGameTargets;
    private Card nextCard;
    private Card chosenCard;
    private Elixir elixir;
    private int numberOfCups;

    public GameAccessory(Player player){
        this.player = player;
        this.hand = createHand();
        this.nextCard = createNextCard();
        this.towers = Factory.createTowers(player);
        this.elixir = new Elixir(4);
        this.chosenCard = null;
        this.numberOfCups = 0;
        this.inGameTargets = new ArrayList<>();
        this.inGameTargets.addAll(towers);
        startTowers();
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

    public Elixir getElixir() {
        return elixir;
    }

    public void setElixir(Elixir elixir) {
        this.elixir = elixir;
    }

    public double healthOfTowers(){
        double temp=0;
        for (Tower tower:towers) {
            temp+=tower.getHealth();
        }
        return temp;
    }

    public boolean isKingInGame(){
        for (Tower tower:towers) {
            if (tower instanceof King){
                return true;
            }
        }
        return false;
    }

    public King getKing(){
        for (Tower tower:towers) {
            if (tower instanceof King){
                return (King) tower;
            }
        }
        return null;
    }

    public int getNumberOfCups() {
        return numberOfCups;
    }

    public void increaseCups(int value){
        numberOfCups= numberOfCups+value;
    }

    public void setNumberOfCups(int numberOfCups) {
        this.numberOfCups = numberOfCups;
    }

    public ArrayList<Vulnerable> getInGameTargets() {
        return inGameTargets;
    }

    public void setInGameTargets(ArrayList<Vulnerable> inGameTargets) {
        this.inGameTargets = inGameTargets;
    }

    public void startTowers(){
        for (Tower tower:towers) {
            tower.start();
        }
    }
}
