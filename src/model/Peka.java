package Model;

public class Peka extends Soldier{

    public Peka(Player player) {
        super(4, 1, 1.8, false, 1, Speed.FAST, player,"./src/View/pic/peka.png");
        setSelf(Target.GROUND);
        setTarget(Target.GROUND);
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(600);setDamage(325);}
            case LEVEL2 ->{setHealth(660);setDamage(357);}
            case LEVEL3 ->{setHealth(726);setDamage(393);}
            case LEVEL4 ->{setHealth(798);setDamage(432);}
            case LEVEL5 ->{setHealth(876);setDamage(474);}
        }
    }

    @Override
    public void run() {

    }
}
