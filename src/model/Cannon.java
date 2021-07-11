package Model;

public class Cannon extends Building{
    public Cannon(Player player) {
        super(3, 5.5, 0.8, 30, player,"./src/View/pic/cannon.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(380);setDamage(60);}
            case LEVEL2 ->{setHealth(418);setDamage(66);}
            case LEVEL3 ->{setHealth(459);setDamage(72);}
            case LEVEL4 ->{setHealth(505);setDamage(79);}
            case LEVEL5 ->{setHealth(554);setDamage(87);}
        }
    }

    @Override
    public void run() {

    }
}
