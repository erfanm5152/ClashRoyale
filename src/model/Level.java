package Model;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.Serializable;

/**
 * The enum Level.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public enum Level implements Serializable {
    /**
     * Level 1 level.
     */
    LEVEL1("../View/pic/1.png"),
    /**
     * Level 2 level.
     */
    LEVEL2("../View/pic/2.png"),
    /**
     * Level 3 level.
     */
    LEVEL3("../View/pic/3.png"),
    /**
     * Level 4 level.
     */
    LEVEL4("../View/pic/4.png"),
    /**
     * Level 5 level.
     */
    LEVEL5("../View/pic/5.png");
    // address of image of level
    private String imageAddress;

    /**
     * create new level
     *
     * @param imageAddress address of image
     */
    Level(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    /**
     * Get level by xp level.
     *
     * @param xp the xp
     * @return the level
     */
    public static Level getLevelByXp(int xp) {
        Level level = LEVEL1;
        if (xp < 500) {
            level = LEVEL1;
        }
        if (500 <= xp && xp < 900) {
            level = LEVEL2;
        }
        if (900 <= xp && xp < 1700) {
            level = LEVEL3;
        }
        if (1700 <= xp && xp < 2500) {
            level = LEVEL4;
        }
        if (2500 <= xp) {
            level = LEVEL5;
        }
        return level;
    }

    /**
     * Gets image address.
     *
     * @return the image address
     */
    public String getImageAddress() {
        return imageAddress;
    }
}
