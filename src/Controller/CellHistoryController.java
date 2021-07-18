package Controller;

import Model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Random;

public class CellHistoryController extends ListCell<Game> {
    private static Random random =new Random();
    private static int red=random.nextInt(255),green= random.nextInt(255), blue=random.nextInt(255);

    @FXML
    private HBox hbox;


    @FXML
    private Label userOne;

    @FXML
    private Label userTwo;

    @FXML
    private Label winner;

    private FXMLLoader loader;

    @Override
    protected void updateItem(Game game, boolean b) {
        super.updateItem(game, b);
        if (b || game == null) {
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("../View/HistoryCell.fxml"));
                loader.setController(this);
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            hbox.setStyle("-fx-background-color: #"+ Color.rgb(red,green,blue).toString().substring(4));
            blue=(blue+10)%255;
            red = (red+10)%255;
            green = (green+10) % 255;
            userOne.setText(game.getWinner().getName());
            userTwo.setText(game.getLoser().getName());
            winner.setText(game.getWinner().getName());
            setGraphic(hbox);
        }
    }
}
