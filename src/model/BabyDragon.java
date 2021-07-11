package Model;

public class BabyDragon extends Soldier{
    public BabyDragon(Player player) {
        super(4, 3, 1.8, true, 1, Speed.FAST, player,"../View/pic/dragon.png");
        setSelf(Target.AIR);
        setTarget(Target.AIR_AND_GROUND);
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(800);setDamage(100);}
            case LEVEL2 ->{setHealth(880);setDamage(110);}
            case LEVEL3 ->{setHealth(968);setDamage(121);}
            case LEVEL4 ->{setHealth(1064);setDamage(133);}
            case LEVEL5 ->{setHealth(1168);setDamage(146);}
        }
    }

    @Override
    public void run() {

    }
}
