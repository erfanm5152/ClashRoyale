package Model;

public class Fireball extends Spell{
    private int damage;

    public Fireball(Player player) {
        super(4, 2.5, player,"../View/pic/fireball.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 -> damage = 325;
            case LEVEL2 -> damage = 357;
            case LEVEL3 -> damage = 393;
            case LEVEL4 -> damage = 432;
            case LEVEL5 -> damage = 474;
        }
    }

    @Override
    public void run() {

    }
}
