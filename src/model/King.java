package Model;

import java.awt.*;

public class King extends Tower {
    private boolean isDisabled;

    public King( Player player ) {
        super(7,1,player,"./src/View/pic/KingTower.png");
        switch (getPlayer().getUser().getLevel()){
            case LEVEL1 ->{setHealth(2400);setDamage(50);}
            case LEVEL2 ->{setHealth(2568);setDamage(53);}
            case LEVEL3 ->{setHealth(2736);setDamage(57);}
            case LEVEL4 ->{setHealth(2904);setDamage(60);}
            case LEVEL5 ->{setHealth(3096);setDamage(64);}
        }
        isDisabled = false;
    }

    @Override
    public void run() {

    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
