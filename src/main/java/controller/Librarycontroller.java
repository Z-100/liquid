package controller;

import classes.Game;
import classes.Stages;
import classes.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class Librarycontroller {

    @FXML
    private Button storeBtn;
    @FXML
    private Button libraryBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private TableView<Game> gamelist;

    private ObservableList<Game> games;

    public void init(Stage primaryStage) {
        Stages stages = new Stages(primaryStage);

        storeBtn.setOnAction(actionEvent -> {
            stages.storepage();
        });

        libraryBtn.setOnAction(actionEvent -> {
            stages.librarypage();
        });

        profileBtn.setOnAction(actionEvent -> {
            stages.profilepage();
        });

        logoutBtn.setOnAction(actionEvent -> {
            UserSession.removeInstance();
            stages.loginpage();
        });


        this.games = FXCollections.observableArrayList();
        for (int i = 0; i < 6; i++) {
            Game game = new Game("SANANAS", "SANANAS", "SANANAS", 15.99);
            games.add(game);
        }

        TableColumn<Game, String> title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory<Game, String>("title"));
        // features -> features.getValue().titleProperty()

        title.prefWidthProperty().bind(gamelist.widthProperty().multiply(1));
        title.setResizable(false);

        gamelist.getColumns().add(title);
        gamelist.setItems(games);
    }
}
