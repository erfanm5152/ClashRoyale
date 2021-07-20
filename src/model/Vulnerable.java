package Model;

import javafx.geometry.Point2D;

public interface Vulnerable {
    void decreaseHealth(int decreaseValue);
    Point2D getPoint2D();
    void effectOfRage();
    void neutralizeRage();
}
