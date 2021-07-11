package Controller;

import Model.SharedData;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class SignUpController {

    private SharedData sharedData = SharedData.getInstance();

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpKey;

    @FXML
    void signUpUser(ActionEvent event) {
        if (userNameTextField.getText().length()!=0&& passwordField.getText().length()!=0){
            User user =new User(userNameTextField.getText(),passwordField.getText());
            if (!sharedData.getUserLibrary().isExistUser(user)){
                sharedData.getUserLibrary().add(user);
                sharedData.setUser(user);
                System.out.println(user);
            }else {
                Alert alert =new Alert(Alert.AlertType.ERROR,"This user exists.", ButtonType.CLOSE);
                alert.showAndWait();
                return;
            }
        }else {
            Alert alert =new Alert(AlertType.NONE,"Fill in the username and password field.",ButtonType.OK);
            alert.showAndWait();
            return;
        }
        MenuController.changeToMenu(userNameTextField);
    }

}
