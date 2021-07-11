package Model;

public class InfernoTower extends Building{

    private int lowDamage;
    private int increaseDamagePerSecond;

    public InfernoTower(Player player) {
        super(5, 6, 0.4, 40,player ,"../View/pic/inferno.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(800);lowDamage = 20;setDamage(400);}
            case LEVEL2 ->{setHealth(880);lowDamage = 22;setDamage(440);}
            case LEVEL3 ->{setHealth(968);lowDamage = 24;setDamage(484);}
            case LEVEL4 ->{setHealth(1064);lowDamage = 26;setDamage(532);}
            case LEVEL5 ->{setHealth(1168);lowDamage = 29;setDamage(584);}
        }
        increaseDamagePerSecond = (int) (Math.floor(getDamage()-lowDamage)/40);
    }

    @Override
    public void run() {

    }
}
