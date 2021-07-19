package View;

import Model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MapView extends Group {
    public final static double CELL_WIDTH = 16.0;

    private int rowCount;
    private int columnCount;
    private ImageView[][] cellViews;
    private Image a ;
    private Image b ;
    private Image C ;
    private Image d ;
    private Image E ;
    private Image e1 ;
    private Image e2 ;
    private Image e3 ;
    private Image e4 ;
    private Image l ;
    private Image m ;
    private Image n1 ;
    private Image n2 ;
    private Image p ;
    private Image p1 ;
    private Image p2 ;
    private Image Q ;
    private Image r ;
    private Image s ;
    private Image t ;
    private Image U ;
    private Image v1 ;
    private Image v2 ;
    private Image v3 ;
    private Image v4 ;
    private Image x1 ;
    private Image x2 ;
    private Image x3 ;
    private Image x4 ;
    private Image y1 ;
    private Image y2 ;
    private Image j1 ;
    private Image j2 ;
    private Image Z ;

    public MapView() {
        a=new Image(getClass().getResourceAsStream("pic/map/a.png"));
        b=new Image(getClass().getResourceAsStream("pic/map/b.png"));
        C=new Image(getClass().getResourceAsStream("pic/map/C.png"));
        d=new Image(getClass().getResourceAsStream("pic/map/d.png"));
        E=new Image(getClass().getResourceAsStream("pic/map/E.png"));
        e1=new Image(getClass().getResourceAsStream("pic/map/e1.png"));
        e2=new Image(getClass().getResourceAsStream("pic/map/e2.png"));
        e3=new Image(getClass().getResourceAsStream("pic/map/e3.png"));
        e4=new Image(getClass().getResourceAsStream("pic/map/e4.png"));
        l=new Image(getClass().getResourceAsStream("pic/map/l.png"));
        m=new Image(getClass().getResourceAsStream("pic/map/m.png"));
        n1=new Image(getClass().getResourceAsStream("pic/map/n1.png"));
        n2=new Image(getClass().getResourceAsStream("pic/map/n2.png"));
        p=new Image(getClass().getResourceAsStream("pic/map/p.png"));
        p1=new Image(getClass().getResourceAsStream("pic/map/p1.png"));
        p2=new Image(getClass().getResourceAsStream("pic/map/p2.png"));
        Q=new Image(getClass().getResourceAsStream("pic/map/Q.png"));
        r=new Image(getClass().getResourceAsStream("pic/map/r.png"));
        s=new Image(getClass().getResourceAsStream("pic/map/s.png"));
        t=new Image(getClass().getResourceAsStream("pic/map/t.png"));
        U=new Image(getClass().getResourceAsStream("pic/map/U.png"));
        v1=new Image(getClass().getResourceAsStream("pic/map/v1.png"));
        v2=new Image(getClass().getResourceAsStream("pic/map/v2.png"));
        v3=new Image(getClass().getResourceAsStream("pic/map/v3.png"));
        v4=new Image(getClass().getResourceAsStream("pic/map/v4.png"));
        x1=new Image(getClass().getResourceAsStream("pic/map/x1.png"));
        x2=new Image(getClass().getResourceAsStream("pic/map/x2.png"));
        x3=new Image(getClass().getResourceAsStream("pic/map/x3.png"));
        x4=new Image(getClass().getResourceAsStream("pic/map/x4.png"));
        y1=new Image(getClass().getResourceAsStream("pic/map/y1.png"));
        y2=new Image(getClass().getResourceAsStream("pic/map/y2.png"));
        j1=new Image(getClass().getResourceAsStream("pic/map/j1.png"));
        j2=new Image(getClass().getResourceAsStream("pic/map/j2.png"));
        Z=new Image(getClass().getResourceAsStream("pic/map/z.png"));
    }
    private void initializeGrid() {
        cellViews = new ImageView[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView imageView = new ImageView();
                imageView.setX(column * CELL_WIDTH);
                imageView.setY(row * CELL_WIDTH);
                imageView.setFitWidth(CELL_WIDTH);
                imageView.setFitHeight(CELL_WIDTH);
                cellViews[row][column] = imageView;
                this.getChildren().add(imageView);
            }
        }
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        initializeGrid();
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        initializeGrid();
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
    public void setImages(Game game){

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (game.getMap()[i][j]==null){return;}
                switch (game.getMap()[i][j]){
                    case "a":
                        cellViews[i][j].setImage(a);
                        break;
                    case "b":
                        cellViews[i][j].setImage(b);
                        break;
                    case "C":
                        cellViews[i][j].setImage(C);
                        break;
                    case "E":
                        cellViews[i][j].setImage(E);
                        break;
                    case "y1":
                        cellViews[i][j].setImage(y1);
                        break;
                    case "y2":
                        cellViews[i][j].setImage(y2);
                        break;
                    case "j1":
                        cellViews[i][j].setImage(j1);
                        break;
                    case "j2":
                        cellViews[i][j].setImage(j2);
                        break;
                    case "Q":
                        cellViews[i][j].setImage(Q);
                        break;
                    case "Z":
                        cellViews[i][j].setImage(Z);
                        break;
                    case "e1":
                        cellViews[i][j].setImage(e1);
                        break;

                    case "e2":
                        cellViews[i][j].setImage(e2);
                        break;
                    case "e3":
                        cellViews[i][j].setImage(e3);
                        break;
                    case "e4":
                        cellViews[i][j].setImage(e4);
                        break;
                    case "m":
                        cellViews[i][j].setImage(m);
                        break;
                    case "n1":
                        cellViews[i][j].setImage(n1);
                        break;
                    case "n2":
                        cellViews[i][j].setImage(n2);
                        break;
                    case "r":
                        cellViews[i][j].setImage(r);
                        break;
                    case "l":
                        cellViews[i][j].setImage(l);
                        break;
                    case "p":
                        cellViews[i][j].setImage(p);
                        break;
                    case "s":
                        cellViews[i][j].setImage(s);
                        break;
                    case "d":
                        cellViews[i][j].setImage(U);
                        break;
                    case "u":
                        cellViews[i][j].setImage(d);
                        break;
                    case "x1":
                        cellViews[i][j].setImage(x1);
                        break;
                    case "x2":
                        cellViews[i][j].setImage(x2);
                        break;
                    case "x3":
                        cellViews[i][j].setImage(x3);
                        break;
                    case "x4":
                        cellViews[i][j].setImage(x4);
                        break;
                    case "v1":
                        cellViews[i][j].setImage(v1);
                        break;
                    case "v2":
                        cellViews[i][j].setImage(v2);
                        break;
                    case "v3":
                        cellViews[i][j].setImage(v3);
                        break;
                    case "v4":
                        cellViews[i][j].setImage(v4);
                        break;
                    case "t":
                        cellViews[i][j].setImage(t);
                        break;
                    case "p1":
                        cellViews[i][j].setImage(p1);
                        break;
                    case "p2":
                        cellViews[i][j].setImage(p2);
                }
            }
        }
    }
}
