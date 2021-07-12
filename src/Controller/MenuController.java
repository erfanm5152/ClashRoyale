package Controller;

import Model.Player;
import Model.SharedData;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

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
    void showHistory(ActionEvent event) {

    }

    @FXML
    void showBattleDeck(ActionEvent event) {
        Stage stage;
        Parent root = null;

        stage = (Stage) profileKey.getScene().getWindow();
        stage.hide();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/BattleDeck.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeSceneAnimation(root,"left",631);
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showProfile(ActionEvent event) {
        Stage stage;
        Parent root = null;

        stage = (Stage) profileKey.getScene().getWindow();
        stage.hide();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/Profile.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeSceneAnimation(root,"left",631);
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(){
        if (sharedData.getUser().getPlayer()==null){
            //todo dorost shavad edamash.
            Player player = new Player(sharedData.getUser());
            sharedData.getUser().setPlayer(player);
        }
    }

    public static void changeToMenu(Node node){
        Stage stage;
        Parent root = null;

        stage = (Stage) node.getScene().getWindow();
        stage.hide();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeSceneAnimation(root,"right",631);
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeSceneAnimation(Parent root,String leftOrRight ,int width){
        int temp1=0;
        int temp2=0;
        switch (leftOrRight){
            case "left":
                temp1 = width;
                break;

            case "right":
                temp1 = -width;
                break;
        }
        KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(root.translateXProperty(), temp1));
        KeyFrame end = new KeyFrame(Duration.seconds(1), new KeyValue(root.translateXProperty(), temp2));
        new Timeline(start,end).play();
    }

}
