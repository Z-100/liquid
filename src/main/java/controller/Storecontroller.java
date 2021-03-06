package controller;

import classes.Conn;
import classes.Game;
import classes.Stages;
import classes.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.SQLException;

public class Storecontroller {

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
    private Label maxpriceLabel;
    @FXML
    private Slider maxpriceSlider;
    @FXML
    private Label displaynameLabelTop;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button buyBtn;
    @FXML
    private Label errorMsgLabel;
    @FXML
    private ToggleGroup os;

    private String gameTitle;
    private String gameDesc;
    private String gameCategory;
    private String gameType;
    private double gamePrice;
    private InputStream gameThumbnail;

    private Double maxprice = 25.0;
    private String selectedos = "Windows";

    private String session_displayname = UserSession.getDisplayName();

    public void init(Stage primaryStage) {

        // Change stages
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
            stages.loginpage();
        });

        displaynameLabelTop.setText(session_displayname);

        refreshBtn.setOnAction(actionEvent -> {
            getGames(25, "Windows");
        });

        buyBtn.setOnAction(actionEvent -> {
            buyGame();
        });


        // Filters for the store
        /*TODO Time wasn't enough: Adding category feature later on*/

        os.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton rad = (RadioButton) os.getSelectedToggle();
            selectedos = rad.getText();
            gameTableView.setItems(getGames(maxprice.intValue(), selectedos));
        });

        maxpriceSlider.setValue(25);
        maxpriceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                double newerValue = newValue.doubleValue();
                maxpriceLabel.setText(String.format("Max. price: %.0f ??",(((newerValue + 4) / 5 * 5) - 4)));
                maxprice = maxpriceSlider.getValue();
            }
        });
        maxpriceSlider.setOnMouseReleased(event -> {
            gameTableView.setItems(getGames(maxprice.intValue(), selectedos));
        });


        // Table with games from DB
        TableColumn<Game, String> title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory<Game, String>("title"));
        title.prefWidthProperty().bind(gameTableView.widthProperty().multiply(0.7));

        TableColumn<Game, String> price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<Game, String>("price"));
        price.prefWidthProperty().bind(gameTableView.widthProperty().multiply(0.3));

        TableColumn<Game, Image> thumbnail = new TableColumn("Thumbnail");
        thumbnail.setCellValueFactory(new PropertyValueFactory<>("thumbnail"));
        thumbnail.prefWidthProperty().bind(gameTableView.widthProperty().multiply(0));

        title.setResizable(false);
        gameTableView.getColumns().addAll(title, price, thumbnail);
        gameTableView.setItems(getGames(25, "Windows"));
    }

    public ObservableList<Game> getGames(int maxprice, String selectedos) {
        errorMsgLabel.setVisible(false);
        Conn conn = new Conn();

        String statement1 = String.format(
                "SELECT game.id, game.title, game.description, game.price, game.thumbnail, category.category, os.type " +
                        "FROM game " +
                                "JOIN tt_game_category ON game.id = tt_game_category.game_id " +
                                "JOIN category ON tt_game_category.category_id = category.id " +
                                "JOIN tt_game_os ON game.id = tt_game_os.game_id " +
                                "JOIN os ON tt_game_os.os_id = os.id " +
                        "WHERE game.price < %s " +
                            "AND os.type = '%s' " +
                        "GROUP BY title",
                maxprice, selectedos
        );

        conn.query(statement1, 0);

        ObservableList<Game> games = FXCollections.observableArrayList();
        try {
            while (conn.getResult().next()) {
                this.gameTitle = conn.getResult().getString("title");
                this.gameDesc = conn.getResult().getString("description");
                this.gameCategory = conn.getResult().getString("category");
                this.gameType = conn.getResult().getString("type");
                this.gamePrice = conn.getResult().getDouble("price");
                this.gameThumbnail = conn.getResult().getBinaryStream("thumbnail");
                games.add(new Game(this.gameTitle, this.gameDesc, this.gameCategory, this.gameType, this.gamePrice, this.gameThumbnail));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in SQL");
        }
        return games;
    }

    public void buyGame() {
        Game selectedGame = gameTableView.getSelectionModel().getSelectedItem();
        Conn conn = new Conn();

        String statement1 = String.format(
                "SELECT tt_user_game.user_id, tt_user_game.game_id " +
                        "FROM tt_user_game " +
                                "JOIN user ON user.id = tt_user_game.user_id " +
                                "JOIN game ON game.id = tt_user_game.game_id " +
                        "WHERE user.username = '%s' AND game.title = '%s'",
                UserSession.getUserName(), selectedGame.getTitle()
        );
        String statement2 = String.format(
                "INSERT INTO tt_user_game (user_id, game_id)\n" +
                        "VALUES ((SELECT id FROM user WHERE username = '%s'), (SELECT id FROM game WHERE title = '%s'))",
                UserSession.getUserName(), selectedGame.getTitle()
        );

        conn.query(statement1, 0);
        try {
            int inDb = 0;
            while (conn.getResult().next()) {
                inDb = 1;
                errorMsgLabel.setVisible(true);
            }
            if (inDb == 0) conn.query(statement2, 1);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("game bought");
        }
    }
}
