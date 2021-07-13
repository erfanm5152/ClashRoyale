import Model.FileUtils;
import Model.SharedData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
        primaryStage.setTitle("Clash Royale");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 349, 532));
        primaryStage.show();
    }

    @Override
    public void stop(){
        FileUtils.writeUsers(SharedData.getInstance().getUserLibrary());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
