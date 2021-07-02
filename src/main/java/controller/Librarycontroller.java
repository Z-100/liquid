package controller;

import classes.Conn;
import classes.Game;
import classes.Stages;
import classes.UserSession;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.SQLException;

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
    private TableView<Game> gameTableView;
    @FXML
    private Label displaynameLabelTop;
    @FXML
    private Label gameDescriptionLabel;
    @FXML
    private ImageView gameThumbnailImage;

    private String gameTitle;
    private String gameDesc;
    private String gameCategory;
    private String gameType;
    private double gamePrice;
    private InputStream gameThumbnail;

    String session_displayname = UserSession.getDisplayName();

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

        displaynameLabelTop.setText(session_displayname);


        TableColumn<Game, String> title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory<Game, String>("title"));
        title.prefWidthProperty().bind(gameTableView.widthProperty().multiply(1));

        TableColumn<Game, String> description = new TableColumn("Description");
        description.setCellValueFactory(new PropertyValueFactory<Game, String>("description"));
        description.prefWidthProperty().bind(gameTableView.widthProperty().multiply(0));

        TableColumn<Game, byte[]> thumbnail = new TableColumn("Thumbnail");
        thumbnail.setCellValueFactory(new PropertyValueFactory<>("thumbnail"));
        thumbnail.prefWidthProperty().bind(gameTableView.widthProperty().multiply(0));

        title.setResizable(false);
        gameTableView.getColumns().addAll(title, description, thumbnail);
        gameTableView.setItems(getGames());

        gameTableView.setOnMouseClicked(mouseEvent -> {
            getDescImg();
        });
    }

    public ObservableList<Game> getGames() {
        String statement1 = String.format(
                "SELECT game.title, game.description, game.thumbnail " +
                "FROM tt_user_game " +
                    "JOIN user ON tt_user_game.user_id = user.id " +
                    "JOIN game ON game.id = tt_user_game.game_id " +
                "WHERE user.username = '%s'",
                UserSession.getUserName()
        );

        Conn conn = new Conn();
        conn.query(statement1, 0);

        ObservableList<Game> games = FXCollections.observableArrayList();
        try {
            while (conn.getResult().next()) {
                this.gameTitle = conn.getResult().getString("title");
                this.gameDesc = conn.getResult().getString("description");
                this.gameCategory = "empty";
                this.gameType = "empty";
                this.gamePrice = 404;
                this.gameThumbnail = conn.getResult().getBinaryStream("thumbnail");
                games.add(new Game(this.gameTitle, this.gameDesc, this.gameCategory, this.gameType, this.gamePrice, this.gameThumbnail));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in SQL");
        }
        return games;
    }

    public void getDescImg() {
        if (this.gameTableView.getSelectionModel().getSelectedItem() != null) {
            gameDescriptionLabel.setText(gameTableView.getSelectionModel().getSelectedItem().getDescription());
            gameThumbnailImage.setImage(new Image(gameTableView.getSelectionModel().getSelectedItem().getThumbnail()));
        } else {
            gameDescriptionLabel.setText("No game selected");
        }
    }
}
