package Model;

public class Wizard extends Soldier{
    public Wizard(Player player) {
        super(5, 5, 1.7, true, 1, Speed.MEDIUM,player ,"../View/pic/wizard.png");
        setSelf(Target.GROUND);
        setTarget(Target.AIR_AND_GROUND);
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(340);setDamage(130);}
            case LEVEL2 ->{setHealth(374);setDamage(143);}
            case LEVEL3 ->{setHealth(411);setDamage(157);}
            case LEVEL4 ->{setHealth(452);setDamage(172);}
            case LEVEL5 ->{setHealth(496);setDamage(189);}
        }
    }

    @Override
    public void run() {

    }
}
