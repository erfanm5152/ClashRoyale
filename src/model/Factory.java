package Model;

import java.util.ArrayList;

/**
 * The type Factory.
 * for create cards & towers with static methods.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class Factory {
    /**
     * Create remaining cards array list.
     *
     * @param player the player
     * @return the array list of cards
     */
    public static ArrayList<Card> createRemainingCards(Player player) {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Fireball(player));
        cards.add(new Arrows(player));
        cards.add(new Cannon(player));
        cards.add(new InfernoTower(player));

        return cards;
    }

    /**
     * Create basic deck array list.
     *
     * @param player the player
     * @return the array list of cards
     */
    public static ArrayList<Card> createBasicDeck(Player player) {
        ArrayList<Card> cards = new ArrayList<>();
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

    /**
     * Create towers array list.
     *
     * @param player the player
     * @return the array list of towers
     */
    public static ArrayList<Tower> createTowers(Player player) {
        ArrayList<Tower> towers = new ArrayList<>();
        if (!(player.getUser() instanceof Bot)) {
            towers.add(new King(player, 157, 502));
            towers.add(new Princess(player, 63, 444));
            towers.add(new Princess(player, 254, 444));
        } else {
            towers.add(new King(player, 157, 58));
            towers.add(new Princess(player, 256, 115));
            towers.add(new Princess(player, 61, 115));
        }
        return towers;
    }

    /**
     * Create replace card.
     *
     * @param oldCard the old card
     * @return new card
     */
    public static Card createReplaceCard(Card oldCard) {
        Card temp = null;
        switch (oldCard.getClass().getName()) {
            case "Model.Archer":
                temp = new Archer(oldCard.getPlayer());
                break;
            case "Model.Barbarians":
                temp = new Barbarians(oldCard.getPlayer());
                break;
            case "Model.BabyDragon":
                temp = new BabyDragon(oldCard.getPlayer());
                break;
            case "Model.Peka":
                temp = new Peka(oldCard.getPlayer());
                break;
            case "Model.Wizard":
                temp = new Wizard(oldCard.getPlayer());
                break;
            case "Model.Valkyrie":
                temp = new Valkyrie(oldCard.getPlayer());
                break;
            case "Model.Giant":
                temp = new Giant(oldCard.getPlayer());
                break;
            case "Model.Rage":
                temp = new Rage(oldCard.getPlayer());
                break;
            case "Model.Fireball":
                temp = new Fireball(oldCard.getPlayer());
                break;
            case "Model.Arrows":
                temp = new Arrows(oldCard.getPlayer());
                break;
            case "Model.Cannon":
                temp = new Cannon(oldCard.getPlayer());
                break;
            case "Model.InfernoTower":
                temp = new InfernoTower(oldCard.getPlayer());
                break;

        }
        return temp;
    }
}
