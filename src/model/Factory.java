package Model;

import java.util.ArrayList;

public class Factory {
    public static ArrayList<Card> createRemainingCards(Player player){
        ArrayList<Card> cards =new ArrayList<>();
        cards.add(new Fireball(player));
        cards.add(new Arrows(player));
        cards.add(new Cannon(player));
        cards.add(new InfernoTower(player));

        return cards;
    }
    public static ArrayList<Card> createBasicDeck(Player player){
        ArrayList<Card>cards =new ArrayList<>();
        cards.add(new Barbarians(player));
        cards.add(new Archer(player));
        cards.add(new BabyDragon(player));
        cards.add(new Peka(player));
        cards.add(new Wizard(player));
        cards.add(new Valkyrie(player));
        cards.add(new Giant(player));
        cards.add(new Rage(player));
        return cards;
    }

    public static ArrayList<Tower> createTowers(Player player){
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(new King(player));
        towers.add(new Princess(player));
        towers.add(new Princess(player));
        return towers;
    }
}
