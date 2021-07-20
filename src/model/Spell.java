package Model;

public abstract class Spell extends Card{


    public Spell(int cost, double range,Player player ,String cardAddress) {
        super(cost, range,player ,cardAddress);
    }

    @Override
    public synchronized void decreaseHealth(int decreaseValue) {}

    @Override
    public void effectOfRage() {
    }

    @Override
    public void neutralizeRage() {

    }
}
