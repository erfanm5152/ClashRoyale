package Controller;

import Model.*;
import View.MapView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


public class GameController {

    private ObservableList<Card> hand;

//    private TimerMe timerMe;

    private Game game;
    @FXML
    private MapView mapView;

    @FXML
    private ImageView newCard;

    @FXML
    private ListView<Card> listOfCards;

    @FXML
    private Label timerLabel;

    @FXML
    private Label elixirLabel;

    @FXML
    private ProgressBar progressBar;

    public void initialize(){
        game = new Game();
        game.setTimerMe(new TimerMe(timerLabel,game));
        game.getTimerMe().start();
        new FinishThread(game).start();
        mapView.setImages(game);
        elixirLabel.textProperty().bind(progressBar.progressProperty().multiply(10).asString("%.0f"));

        game.getUser1().getPlayer().setGame(game);
        game.getUser2().getPlayer().setGame(game);

        game.getUser1().getPlayer().setGameAccessory(new GameAccessory(game.getUser1().getPlayer()));
        game.getUser2().getPlayer().setGameAccessory(new GameAccessory(game.getUser2().getPlayer()));


        ((Bot)game.getUser2()).setMapView(mapView);

        hand = FXCollections.observableArrayList(game.getUser1().getPlayer().getGameAccessory().getHand());
        newCard.setImage(new Image(getClass().getResourceAsStream(game.getUser1().getPlayer().getGameAccessory().getNextCard().getCardImageAddress())));
        listOfCards.setItems(hand);
        listOfCards.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> cardListView) {
                return new HandCellController();
            }
        });
        listOfCards.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Card>() {
                    @Override
                    public void changed(ObservableValue<? extends Card> observableValue, Card card, Card t1) {
                        game.getUser1().getPlayer().getGameAccessory().setChosenCard(t1);
                    }
                }
        );
        progressBar.progressProperty().bind(game.getUser1().getPlayer().getGameAccessory().getElixir().elixirValueProperty().divide(10));
    }
//todo yek thread ham baraye inke chek cone bazitamam shode ya na;
    @FXML
    public void handle(MouseEvent mouseEvent) {
        // todo check shavad ke pol kart entekhabi ra dard ya na.
        Card chosenCard =game.getUser1().getPlayer().getGameAccessory().getChosenCard();
        if (chosenCard == null){return;}

        if (chosenCard.getCost()>game.getUser1().getPlayer().getGameAccessory().getElixir().getElixirValue()){
            return;
        }
        //todo az comment dar biad
//        game.getUser1().getPlayer().getGameAccessory().getElixir().add(chosenCard.getCost()*-1);

        chosenCard.setPoint2D(new Point2D(mouseEvent.getX(),mouseEvent.getY()));
        chosenCard.setMap(mapView);
        chosenCard.setImageView(new ImageView());
        //todo az comment dar biad , age instance of spell nabod bayd be card haye daron bazi add shavad va marz check shavad
        if (!(chosenCard instanceof Spell)){
            game.getUser1().getPlayer().getGameAccessory().getInGameTargets().add(chosenCard);
        }
        game.getInMapCards().add(chosenCard);

        chosenCard.start();
        System.out.println(mouseEvent.getX() +" "+mouseEvent.getY());
        /*
        age az noe spell bod nyazi be check kardan marz nistl
         */

        Card nextCard =game.getUser1().getPlayer().getGameAccessory().getNextCard();
        //remove chosen Card from hand & deck
        hand.remove(chosenCard);
        game.getUser1().getPlayer().getGameAccessory().getHand().remove(chosenCard);
        game.getUser1().getPlayer().getDeck().remove(chosenCard);
        //add new of old card to deck for refresh health ,...
        game.getUser1().getPlayer().getDeck().add(Factory.createReplaceCard(chosenCard));
        //add next card to hand
        hand.add(nextCard);
        game.getUser1().getPlayer().getGameAccessory().getHand().add(nextCard);
        //create new next card
        Card createdNextCard = game.getUser1().getPlayer().getGameAccessory().createNextCard();
        game.getUser1().getPlayer().getGameAccessory().setNextCard(createdNextCard);
        newCard.setImage(new Image(getClass().getResourceAsStream(createdNextCard.getCardImageAddress())));
    }
}
