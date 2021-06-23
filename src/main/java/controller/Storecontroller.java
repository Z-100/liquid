package controller;

import classes.Game;
import classes.Stages;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private RadioButton windows;
    @FXML
    private RadioButton linux;
    @FXML
    private RadioButton macos;
    @FXML
    private CheckBox actionBox;
    @FXML
    private CheckBox horrorBox;
    @FXML
    private CheckBox indieBox;
    @FXML
    private CheckBox tripleaBox;
    @FXML
    private CheckBox doubleabox;
    @FXML
    private CheckBox aBox;


    int editToggle = 0;

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
            stages.loginpage();
        });

        double priceVal = maxpriceSlider.getValue();
        //TODO Do some slider stuff
//        maxpriceSlider.setOnDragDetected(System.out::println(maxpriceSlider.getValue());

    }
}
