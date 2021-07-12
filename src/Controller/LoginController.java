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

public class LoginController {

    private SharedData sharedData = SharedData.getInstance();

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginKey;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    void login(ActionEvent event) {
        String name="";
        String password="";
        if (userNameTextField.getText().length()!=0 && passwordField.getText().length()!=0){
            name= userNameTextField.getText();
            password =passwordField.getText();
            if (sharedData.getUserLibrary().isExistUser(name)){
                if (sharedData.getUserLibrary().checkPassword(name,password)){
                    sharedData.setUser(sharedData.getUserLibrary().getUserByName(name));
                }else {
                    Alert alert =new Alert(AlertType.ERROR,"Wrong password.",ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
            }else {
                Alert alert =new Alert(AlertType.ERROR,"This user does not exist.",ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }else {
            Alert alert =new Alert(Alert.AlertType.NONE,"Fill in the username and password field.",ButtonType.OK);
            alert.showAndWait();
            return;
        }
        MenuController.changeToMenu(userNameTextField);
    }

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
        MenuController.changeSceneAnimation(root,"left",349);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
