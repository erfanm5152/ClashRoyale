package Controller;

import Model.Game;
import Model.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Finish controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class FinishController {
    // shared data of the game
    private SharedData sharedData = SharedData.getInstance();
    // background
    @FXML
    private VBox vbox;
    // winner name
    @FXML
    private Label winnerName;
    // image of condition
    @FXML
    private ImageView winOrLose;
    // image of cups
    @FXML
    private ImageView stars;
    // home button
    @FXML
    private Button homeKey;

    /**
     * Initialize.
     */
    public void initialize() {
        Game game = sharedData.getUser().getGameHistory().get(sharedData.getUser().getGameHistory().size() - 1);

        winnerName.setText(game.getWinner().getName());

        if (game.getWinner().equals(sharedData.getUser())) {
            winOrLose.setImage(new Image(getClass().getResourceAsStream("../View/pic/win.gif")));
        } else {
            winOrLose.setImage(new Image(getClass().getResourceAsStream("../View/pic/lose.gif")));
        }
        switch (game.getUser1().getPlayer().getGameAccessory().getNumberOfCups()) {
            case 0:
                stars.setImage(new Image(getClass().getResourceAsStream("../View/pic/0 stars.png")));
                break;
            case 1:
                stars.setImage(new Image(getClass().getResourceAsStream("../View/pic/1 stars.png")));
                break;
            case 2:
                stars.setImage(new Image(getClass().getResourceAsStream("../View/pic/2 stars.png")));
                break;
            case 3:
                stars.setImage(new Image(getClass().getResourceAsStream("../View/pic/3 stars.png")));
                break;
        }
    }

    /**
     * Back to home.
     *
     * @param event the event
     */
    @FXML
    void backToHome(ActionEvent event) {
        MenuController.changeToMenu(homeKey);
    }

    /**
     * Finish view.
     *
     * @param node the node
     */
    public static void finishView(Node node) {
        Stage stage;
        Parent root = null;

        stage = (Stage) node.getScene().getWindow();
        try {
            root = FXMLLoader.load(MenuController.class.getResource("../View/Finish.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        MenuController.changeSceneAnimation(root,"right",300);
        stage.hide();
        stage.setHeight(530);
        stage.setWidth(350);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
