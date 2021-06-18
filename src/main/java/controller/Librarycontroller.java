package controller;

import classes.Stages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Librarycontroller {

    @FXML
    private Button storeBtn;
    @FXML
    private Button libraryBtn;
    @FXML
    private TableView<String> gamelist;

    ObservableList<String> games;

    public void init(Stage primaryStage) {

        this.games = FXCollections.observableArrayList();
        for (int i = 0; i < 6; i++) {
            String title = "SANANAS";
            games.add(title);
        }

        Stages stages = new Stages(primaryStage);
        storeBtn.setOnAction(actionEvent -> {
            stages.storepage();
        });
        libraryBtn.setOnAction(actionEvent -> {
            stages.librarypage();
        });

        TableColumn<String, String> title = new TableColumn("Title");
        title.setCellFactory(TextFieldTableCell.forTableColumn());
        title.setCellValueFactory(
                new PropertyValueFactory<>("Title")
        );

        title.prefWidthProperty().bind(gamelist.widthProperty().multiply(1));
        title.setResizable(false);
        gamelist.setItems(games);
        gamelist.getColumns().addAll(title);
    }
}
