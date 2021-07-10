package Controller;

import Model.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController{
    private SharedData sharedData = SharedData.getInstance();



    @FXML
    private Button playKey;

    @FXML
    private Button profileKey;

    @FXML
    private Button battleDeck;

    @FXML
    private Button history;


    @FXML
    void play(ActionEvent event) {

    }

    @FXML
    void showBattleDeck(ActionEvent event) {

    }

    @FXML
    void showHistory(ActionEvent event) {

    }

    @FXML
    void showProfile(ActionEvent event) {
        Stage stage;
        Parent root = null;

        stage = (Stage) profileKey.getScene().getWindow();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/Profile.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeToMenu(Node node){
        Stage stage;
        Parent root = null;

        stage = (Stage) node.getScene().getWindow();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setHeight(460);
        stage.setWidth(630);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
