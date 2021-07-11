package Model;

public class Valkyrie extends Soldier{
    public Valkyrie(Player player) {
        super(4, 1, 1.5, true, 1, Speed.MEDIUM, player,"./src/View/pic/valkyrie.png");
        setSelf(Target.GROUND);
        setTarget(Target.GROUND);
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(880);setDamage(120);}
            case LEVEL2 ->{setHealth(968);setDamage(132);}
            case LEVEL3 ->{setHealth(1064);setDamage(145);}
            case LEVEL4 ->{setHealth(1170);setDamage(159);}
            case LEVEL5 ->{setHealth(1284);setDamage(175);}
        }
    }

    @Override
    public void run() {

    }
}
