package Controller;

import Model.Game;
import Model.SharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * The type History controller.
 *
 * @author Erfanm5152
 * @version 0.1
 */
public class HistoryController {
    // shared data of the controller
    private SharedData sharedData = SharedData.getInstance();
    // observable list for list view
    private final ObservableList<Game> history = FXCollections.observableArrayList(sharedData.getUser().getGameHistory());

    // list view for history of games
    @FXML
    private ListView<Game> listOfGames;
    // home button
    @FXML
    private Button homeKey;

    /**
     * Back to menu.
     *
     * @param event the event
     */
    @FXML
    void backToMenu(ActionEvent event) {
        MenuController.changeToMenu(homeKey);
    }

    /**
     * Initialize.
     */
    public void initialize() {
        listOfGames.setItems(history);
        listOfGames.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() {
            @Override
            public ListCell<Game> call(ListView<Game> gameListView) {
                return new CellHistoryController();
            }
        });
    }

}
