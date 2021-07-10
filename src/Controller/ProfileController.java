package Controller;

import Model.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        levelValue.setText(sharedData.getPlayer().getLevel().getLevelNumber());
        userNameText.setText(sharedData.getPlayer().getName());
        xpValue.setText(String.valueOf(sharedData.getPlayer().getXp()));
        numberOfCups.setText(String.valueOf(sharedData.getPlayer().getNumberOfCups()));
    }

    @FXML
    void backToMenu(ActionEvent event) {
        MenuController.changeToMenu(backKey);
    }

}
