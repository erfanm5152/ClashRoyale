package Model;

import javafx.geometry.Point2D;

/**
 * The interface Vulnerable.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public interface Vulnerable {
    /**
     * Decrease health.
     *
     * @param decreaseValue the decrease value
     */
    void decreaseHealth(int decreaseValue);

    /**
     * Gets point 2 d.
     *
     * @return the point 2 d
     */
    Point2D getPoint2D();

    /**
     * Effect of rage.
     */
    void effectOfRage();

    /**
     * Neutralize rage.
     */
    void neutralizeRage();
}
