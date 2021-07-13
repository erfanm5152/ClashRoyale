package Controller;

import Model.Game;
import View.MapView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class GameController {

    @FXML
    private MapView mapView;

    private Game game;

    @FXML
    private ImageView newCard;

    @FXML
    private ListView<Integer> listOfCards;

    @FXML
    private Label timerLabel;

    @FXML
    private Label elixirLabel;

    @FXML
    private ProgressBar progressBar;

    public void initialize(){
        game = new Game();
        mapView.setImages(game);
    }
}
