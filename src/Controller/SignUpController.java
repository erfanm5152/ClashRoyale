package Controller;

import Model.SharedData;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * The type Sign up controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class SignUpController {
    // shared data of the game
    private SharedData sharedData = SharedData.getInstance();
    // user name text field
    @FXML
    private TextField userNameTextField;
    // password field
    @FXML
    private PasswordField passwordField;
    // signUp button
    @FXML
    private Button signUpKey;

    /**
     * Sign up user.
     * on action of signUp button
     *
     * @param event the event
     */
    @FXML
    void signUpUser(ActionEvent event) {
        if (userNameTextField.getText().length() != 0 && passwordField.getText().length() != 0) {
            User user = new User(userNameTextField.getText(), passwordField.getText());
            if (!sharedData.getUserLibrary().isExistUser(user)) {
                sharedData.getUserLibrary().add(user);
                sharedData.setUser(user);
                System.out.println(user);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This user exists.", ButtonType.CLOSE);
                alert.showAndWait();
                return;
            }
        } else {
            Alert alert = new Alert(AlertType.NONE, "Fill in the username and password field.", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        MenuController.changeToMenu(userNameTextField);
    }

}
