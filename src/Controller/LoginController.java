package Controller;

import Model.SharedData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Login controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class LoginController {

    // shared data of the game
    private SharedData sharedData = SharedData.getInstance();
    // user name text field
    @FXML
    private TextField userNameTextField;
    // password field
    @FXML
    private PasswordField passwordField;
    // button for login
    @FXML
    private Button loginKey;
    // hyperlink for signUp
    @FXML
    private Hyperlink signUpLink;

    /**
     * Login.
     * on action of login button
     *
     * @param event the event
     */
    @FXML
    void login(ActionEvent event) {
        String name = "";
        String password = "";
        if (userNameTextField.getText().length() != 0 && passwordField.getText().length() != 0) {
            name = userNameTextField.getText();
            password = passwordField.getText();
            if (sharedData.getUserLibrary().isExistUser(name)) {
                if (sharedData.getUserLibrary().checkPassword(name, password)) {
                    sharedData.setUser(sharedData.getUserLibrary().getUserByName(name));
                } else {
                    Alert alert = new Alert(AlertType.ERROR, "Wrong password.", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR, "This user does not exist.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Fill in the username and password field.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        MenuController.changeToMenu(userNameTextField);
    }

    /**
     * Sign up.
     * on action of signUp hyperlink
     *
     * @param event the event
     */
    @FXML
    void signUp(ActionEvent event) {
        Stage stage;
        Parent root = null;

        stage = (Stage) signUpLink.getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("../View/SignUp.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController.changeSceneAnimation(root, "left", 349);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
