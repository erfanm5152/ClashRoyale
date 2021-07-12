package Controller;

import Model.Card;
import Model.SharedData;
import View.CellOfList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ProfileController {

    private SharedData sharedData = SharedData.getInstance();

    @FXML
    private Button backKey;

    @FXML
    private ListView<Card> listOfCards;

    @FXML
    private ImageView levelImage;

    @FXML
    private Label xpValue;

    @FXML
    private Label numberOfCups;

    @FXML
    private Label userNameText;


    public void initialize(){
        levelImage.setImage(new Image(getClass().getResource(sharedData.getUser().getLevel().getImageAddress()).toExternalForm()));
        userNameText.setText(sharedData.getUser().getName());
        xpValue.setText(String.valueOf(sharedData.getUser().getXp()));
        numberOfCups.setText(String.valueOf(sharedData.getUser().getNumberOfCups()));
        initializeListView();
    }

    public void initializeListView(){
    final ObservableList<Card> cards = FXCollections.observableArrayList(sharedData.getUser().getPlayer().getDeck());
        listOfCards.setItems(cards);
        listOfCards.setCellFactory(new CellOfList());
    }

    @FXML
    void backToMenu(ActionEvent event) {
        MenuController.changeToMenu(backKey);
    }

}
