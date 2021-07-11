package View;

import Controller.CellViewerController;
import Model.Card;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CellOfList implements Callback<ListView<Card>, ListCell<Card>> {

    @Override
    public ListCell<Card> call(ListView<Card> cardListView) {
        return new CellViewerController();
    }

}
