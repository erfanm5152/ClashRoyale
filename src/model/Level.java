package Model;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.Serializable;

public enum Level implements Serializable {
    LEVEL1("1"),LEVEL2("2"),LEVEL3("3"),
    LEVEL4("4"),LEVEL5("5");

    private String levelNumber;

    Level(String levelNumber) {
        this.levelNumber =levelNumber;
    }

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

    public String getLevelNumber() {
        return levelNumber;
    }
}
