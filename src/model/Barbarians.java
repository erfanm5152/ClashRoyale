package Model;

public class Barbarians extends Soldier{
    public Barbarians(Player player) {
        super(5, 1, 1.5,false,4,Speed.MEDIUM,player,"./src/View/pic/barbarians.png");
        setTarget(Target.GROUND);
        setSelf(Target.GROUND);
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(300);setDamage(75);}
            case LEVEL2 ->{setHealth(330);setDamage(82);}
            case LEVEL3 ->{setHealth(363);setDamage(90);}
            case LEVEL4 ->{setHealth(438);setDamage(99);}
            case LEVEL5 ->{setHealth(480);setDamage(109);}
        }
    }

    @Override
    public void run() {

    }
}
