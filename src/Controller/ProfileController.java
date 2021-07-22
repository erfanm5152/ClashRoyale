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

/**
 * The type Profile controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class ProfileController {
    // shared data of the game
    private SharedData sharedData = SharedData.getInstance();
    // home button
    @FXML
    private Button backKey;
    // list of deck cards
    @FXML
    private ListView<Card> listOfCards;
    // image of level
    @FXML
    private ImageView levelImage;
    // value of xp
    @FXML
    private Label xpValue;
    // number of cards
    @FXML
    private Label numberOfCups;
    // user name
    @FXML
    private Label userNameText;


    /**
     * Initialize.
     * set the required values
     */
    public void initialize() {
        levelImage.setImage(new Image(getClass().getResource(sharedData.getUser().getLevel().getImageAddress()).toExternalForm()));
        userNameText.setText(sharedData.getUser().getName());
        xpValue.setText(String.valueOf(sharedData.getUser().getXp()));
        numberOfCups.setText(String.valueOf(sharedData.getUser().getNumberOfCups()));
        initializeListView();
    }

    /**
     * Initialize list view.
     */
    public void initializeListView() {
        final ObservableList<Card> cards = FXCollections.observableArrayList(sharedData.getUser().getPlayer().getDeck());
        listOfCards.setItems(cards);
        listOfCards.setCellFactory(new CellOfList());
    }

    /**
     * Back to menu.
     *
     * @param event the event
     */
    @FXML
    void backToMenu(ActionEvent event) {
        MenuController.changeToMenu(backKey);
    }

}
