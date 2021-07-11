package Model;

public class Giant extends Soldier{
    public Giant(Player player) {
        super(5,1,1.5,false,1,Speed.SLOW,player,"../View/pic/giant.png");
        setSelf(Target.GROUND);
        setTarget(Target.BUILDINGS);
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(2000);setDamage(126);}
            case LEVEL2 ->{setHealth(2200);setDamage(138);}
            case LEVEL3 ->{setHealth(2420);setDamage(152);}
            case LEVEL4 ->{setHealth(2660);setDamage(167);}
            case LEVEL5 ->{setHealth(2920);setDamage(183);}
        }
    }

    @Override
    public void run() {

    }
}
