package Model;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.Serializable;

public enum Level implements Serializable {
    LEVEL1("../View/pic/1.png"),LEVEL2("../View/pic/2.png"),LEVEL3("../View/pic/3.png"),
    LEVEL4("../View/pic/4.png"),LEVEL5("../View/pic/5.png");

    private String imageAddress;

    Level(String imageAddress) {
        this.imageAddress =imageAddress;
    }

    public static Level getLevelByXp(int xp){
        Level level = LEVEL1;
        if (xp<500){
            level = LEVEL1;
        }
        if (500<=xp && xp<900){
            level = LEVEL2;
        }
        if (900<=xp && xp<1700){
            level = LEVEL3;
        }
        if (1700<=xp && xp<2500){
            level = LEVEL4;
        }
        if (2500<=xp){
            level = LEVEL5;
        }
        return level;
    }

    public String getImageAddress() {
        return imageAddress;
    }
}
