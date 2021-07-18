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

public class HistoryController {

    private SharedData sharedData =SharedData.getInstance();
    private final ObservableList<Game> history = FXCollections.observableArrayList(sharedData.getUser().getGameHistory());

    @FXML
    private ListView<Game> listOfGames;

    @FXML
    private Button homeKey;

    @FXML
    private PieChart chart;

    @FXML
    void backToMenu(ActionEvent event) {
        MenuController.changeToMenu(homeKey);
    }

    public void initialize(){
        listOfGames.setItems(history);
        listOfGames.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() {
            @Override
            public ListCell<Game> call(ListView<Game> gameListView) {
                return new CellHistoryController();
            }
        });
        chart.setLegendVisible(true);
    }

}
