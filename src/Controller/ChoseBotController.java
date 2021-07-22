package Controller;

import Model.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Chose bot controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class ChoseBotController {
    // shared data of the game
    private SharedData sharedData = SharedData.getInstance();

    @FXML
    private RadioButton smart;

    @FXML
    private ToggleGroup botType;

    @FXML
    private RadioButton simple;

    /**
     * Play.
     *
     * @param event the event
     */
    @FXML
    void play(ActionEvent event) {
        if (botType.getSelectedToggle().equals(smart)){
            sharedData.setNameOfBot("smart");
        }else {
            sharedData.setNameOfBot("simple");
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/GameView.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        GameController gameController = loader.getController();
        Stage stage = ((Stage) simple.getScene().getWindow());
        stage.hide();
        stage.setHeight(770);
        stage.setWidth(345);
        MenuController.changeSceneAnimation(root, "left", 320);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
