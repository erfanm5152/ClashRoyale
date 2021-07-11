package Model;

public class Arrows extends Spell{
    private int damage;
    public Arrows(Player player) {
        super(3,4,player ,"../View/pic/arrows.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 -> damage = 144;
            case LEVEL2 -> damage = 156;
            case LEVEL3 -> damage = 174;
            case LEVEL4 -> damage = 189;
            case LEVEL5 -> damage = 210;
        }
    }

    @Override
    public void run() {

    }
}
