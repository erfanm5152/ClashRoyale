package Controller;

import Model.Card;
import Model.SharedData;
import View.CellOfList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.security.SecureRandom;
import java.util.ArrayList;

public class BattleDeckController {
    private SharedData sharedData = SharedData.getInstance();
    private final ObservableList<Card> deck = FXCollections.observableArrayList(sharedData.getUser().getPlayer().getDeck());
    private final ObservableList<Card> reminder = FXCollections.observableArrayList(sharedData.getUser().getPlayer().getReminderCards());

    @FXML
    private Button backKey;

    @FXML
    private Button addKey;

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
        if (sharedData.getUser().getPlayer().getDeck().size()==8) {
            MenuController.changeToMenu(backKey);
        }else {
            new Alert(Alert.AlertType.WARNING,"The number of cards must be eight.", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void addCard(ActionEvent event) {
        Card card =reminderCards.getSelectionModel().getSelectedItem();
        if (card==null ||reminderCards.getSelectionModel().isEmpty()){return;}
        if (sharedData.getUser().getPlayer().getDeck().size()==8){
            new Alert(Alert.AlertType.WARNING,"No card can be added.",ButtonType.OK).showAndWait();
            return;
        }
        sharedData.getUser().getPlayer().getDeck().add(card);
        deck.add(card);
        sharedData.getUser().getPlayer().getReminderCards().remove(card);
        reminder.remove(card);
    }

    @FXML
    void randomChose(ActionEvent event) {
        ArrayList<Card> temp = new ArrayList<>(deck);
        temp.addAll(reminder);
        reminder.removeAll(temp);
        deck.removeAll(temp);
        sharedData.getUser().getPlayer().getDeck().removeAll(temp);
        sharedData.getUser().getPlayer().getReminderCards().removeAll(temp);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt(temp.size());
            deck.add(temp.get(randomNumber));
            sharedData.getUser().getPlayer().getDeck().add(temp.get(randomNumber));
            temp.remove(randomNumber);
        }
        sharedData.getUser().getPlayer().getReminderCards().addAll(temp);
        reminder.addAll(temp);
    }

    @FXML
    void removeCard(ActionEvent event) {
        Card card = deckCards.getSelectionModel().getSelectedItem();
        if (card==null ||deckCards.getSelectionModel().isEmpty()){
            return;
        }
        sharedData.getUser().getPlayer().getDeck().remove(card);
        deck.remove(card);
        sharedData.getUser().getPlayer().getReminderCards().add(card);
        reminder.add(card);
    }

}
