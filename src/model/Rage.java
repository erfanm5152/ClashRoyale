package Model;

public class Rage extends Spell{
    private double duration;
    public Rage(Player player) {
        super(3, 5, player,"./src/View/pic/rage.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 -> duration = 6;
            case LEVEL2 -> duration = 6.5;
            case LEVEL3 -> duration = 7;
            case LEVEL4 -> duration = 7.5;
            case LEVEL5 -> duration = 8;
        }
    }

    @Override
    public void run() {

    }
}
