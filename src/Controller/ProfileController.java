package Controller;

import Model.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ProfileController {

    private SharedData sharedData = SharedData.getInstance();

    @FXML
    private Button backKey;

    @FXML
    private ListView<?> listOfCards;

    @FXML
    private Label levelValue;

    @FXML
    private Label xpValue;

    @FXML
    private Label numberOfCups;

    @FXML
    private Label userNameText;


    public void initialize(){
        levelValue.setText(sharedData.getUser().getLevel().getLevelNumber());
        userNameText.setText(sharedData.getUser().getName());
        xpValue.setText(String.valueOf(sharedData.getUser().getXp()));
        numberOfCups.setText(String.valueOf(sharedData.getUser().getNumberOfCups()));
    }

    @FXML
    void backToMenu(ActionEvent event) {
        MenuController.changeToMenu(backKey);
    }

}
