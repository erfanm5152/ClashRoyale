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

/**
 * The type Menu controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class MenuController {
    // shared data of the game
    private SharedData sharedData = SharedData.getInstance();


    // play button
    @FXML
    private Button playKey;
    // profile button
    @FXML
    private Button profileKey;
    // deck button
    @FXML
    private Button battleDeck;
    // history button
    @FXML
    private Button history;


    /**
     * on action of Play button.
     *
     * @param event the event
     */
    @FXML
    void play(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../View/ChoseBot.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
//        GameController gameController = loader.getController();
        Stage stage = ((Stage) playKey.getScene().getWindow());
        stage.hide();
        stage.setHeight(170);
        stage.setWidth(250);
        changeSceneAnimation(root, "left", 320);
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * on action of history button.
     *
     * @param event the event
     */
    @FXML
    void showHistory(ActionEvent event) {
        Stage stage;
        Parent root = null;

        stage = (Stage) profileKey.getScene().getWindow();
        stage.hide();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/History.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeSceneAnimation(root, "left", 631);
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * on action of deck button.
     *
     * @param event the event
     */
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
        changeSceneAnimation(root, "left", 631);
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * on action of profile button.
     *
     * @param event the event
     */
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
        changeSceneAnimation(root, "left", 631);
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initialize for controller.
     * check the player of the main user.
     */
    public void initialize() {
        if (sharedData.getUser().getPlayer() == null) {
            Player player = new Player(sharedData.getUser());
            sharedData.getUser().setPlayer(player);
        }
    }

    /**
     * static method for change to menu.
     *
     * @param node the node
     */
    public static void changeToMenu(Node node) {
        Stage stage;
        Parent root = null;

        stage = (Stage) node.getScene().getWindow();
        stage.hide();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/Menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeSceneAnimation(root, "right", 631);
        stage.setHeight(460);
        stage.setWidth(631);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * animation for changing scene.
     *
     * @param root        the root (Parent of scene)
     * @param leftOrRight the left or right (direction of animation)
     * @param width       the width of stage
     */
    public static void changeSceneAnimation(Parent root, String leftOrRight, int width) {
        int temp1 = 0;
        int temp2 = 0;
        switch (leftOrRight) {
            case "left":
                temp1 = width;
                break;

            case "right":
                temp1 = -width;
                break;
        }
        KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(root.translateXProperty(), temp1));
        KeyFrame end = new KeyFrame(Duration.seconds(1), new KeyValue(root.translateXProperty(), temp2));
        new Timeline(start, end).play();
    }

}
