package Model;

import java.io.Serializable;

public enum Level implements Serializable {
    LEVEL1,LEVEL2,LEVEL3,LEVEL4,LEVEL5;

    public static Level getLevelByXp(int xp){
        Level level = LEVEL1;
        if (xp<300){
            level = LEVEL1;
        }
        else if (xp<500){
            level = LEVEL2;
        }
        else if (xp<900){
            level = LEVEL3;
        }
        else if (xp<1700){
            level = LEVEL4;
        }
        else if (xp<2500){
            level = LEVEL5;
        }
        return level;
    }
}
