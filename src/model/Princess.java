package Model;


public class Princess extends Tower{

    public Princess(Player player,double x ,double y) {
        super(x,y,7.5,0.8,player,"./src/View/pic/princessTower.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{ setHealth(1400);setDamage(50);}
            case LEVEL2 ->{setHealth(1512);setDamage(54);}
            case LEVEL3 ->{setHealth(1624);setDamage(58);}
            case LEVEL4 ->{setHealth(1750);setDamage(62);}
            case LEVEL5 ->{setHealth(1890);setDamage(69);}
        }
    }

    @Override
    public void run() {

        if (getHealth()<=0){
            stop();
            getPlayer().getGameAccessory().getKing().setDisabled(true);
            getPlayer().getGameAccessory().getTowers().remove(this);
            getPlayer().getGameAccessory().getInGameTargets().remove(this);
            getPlayer().getGame().getOpponent(getPlayer().getUser()).getPlayer().getGameAccessory().increaseCups(1);
        }
    }

}
