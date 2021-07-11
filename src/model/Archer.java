package Model;

public class Archer extends Soldier{
    public Archer(Player player) {
        super(3, 5, 1.2, false, 2, Speed.MEDIUM,player, "./src/View/pic/archer.png");
        setSelf(Target.GROUND);
        setTarget(Target.AIR_AND_GROUND);
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(125);setDamage(33);}
            case LEVEL2 ->{setHealth(127);setDamage(44);}
            case LEVEL3 ->{setHealth(151);setDamage(48);}
            case LEVEL4 ->{setHealth(166);setDamage(53);}
            case LEVEL5 ->{setHealth(182);setDamage(58);}
        }
    }

    @Override
    public void run() {

    }
}
