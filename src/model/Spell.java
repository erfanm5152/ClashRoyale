package Model;

/**
 * The type Spell.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public abstract class Spell extends Card {


    /**
     * Instantiates a new Spell.
     *
     * @param cost        the cost
     * @param range       the range
     * @param player      the player
     * @param cardAddress the card address
     */
    public Spell(int cost, double range, Player player, String cardAddress) {
        super(cost, range, player, cardAddress);
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {
    }

    @Override
    public void effectOfRage() {
    }

    @Override
    public void neutralizeRage() {

    }
}
