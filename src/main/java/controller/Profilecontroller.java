package controller;

import classes.Stages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Profilecontroller {

    @FXML
    Button storeBtn;
    @FXML
    Button libraryBtn;

    public void init(Stage primaryStage) {

        Stages stages = new Stages(primaryStage);

        storeBtn.setOnAction(actionEvent -> {
            stages.storepage();
        });
        libraryBtn.setOnAction(actionEvent -> {
            stages.librarypage();
        });
    }
}
