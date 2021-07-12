package Controller;

import Model.Card;
import Model.SharedData;
import View.CellOfList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class BattleDeckController {
    private SharedData sharedData = SharedData.getInstance();
    private final ObservableList<Card> deck = FXCollections.observableArrayList(sharedData.getUser().getPlayer().getDeck());
    private final ObservableList<Card> reminder = FXCollections.observableArrayList(sharedData.getUser().getPlayer().getReminderCards());

    @FXML
    private Button backKey;

    @FXML
    private Button choseKey;

    @FXML
    private Button removeKey;

    @FXML
    private Button randomKey;

    @FXML
    private ListView<Card> deckCards;

    @FXML
    private ListView<Card> reminderCards;

    public void initialize(){
        deckCards.setItems(deck);
        reminderCards.setItems(reminder);
        deckCards.setCellFactory(new CellOfList());
        reminderCards.setCellFactory(new CellOfList());
    }

    @FXML
    void backToMenu(ActionEvent event) {
        MenuController.changeToMenu(backKey);
    }

    @FXML
    void choseCard(ActionEvent event) {

    }

    @FXML
    void randomChose(ActionEvent event) {

    }

    @FXML
    void removeCard(ActionEvent event) {

    }

}
