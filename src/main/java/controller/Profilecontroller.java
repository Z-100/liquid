package controller;

import classes.Stages;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Profilecontroller {

    @FXML
    private Button storeBtn;
    @FXML
    private Button libraryBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private TextField displaynameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button submitBtn;
    @FXML
    private Button editToggleBtn;

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


        submitBtn.setOnAction(actionEvent -> {
            String displayname = displaynameField.getText();
            String password = passwordField.getText();
            String confirm = confirmField.getText();
            if (!displayname.isEmpty() && !password.isEmpty() && !confirm.isEmpty()) {
                changeDetails(displayname, password, confirm);
            } else {
                errorLabel.setText("Please fill in all the information needed");
            }
        });

        editToggleBtn.setOnAction(actionEvent -> {
            if (editToggle == 0) {
                editToggle = 1;
            } else {
                editToggle = 0;
            }
            toggle();
        });
    }

    private void changeDetails(String displayname, String password, String confirm) {
        // TODO DO SQL STUFF
        errorLabel.setText("Successfully changed acc info");
    }

    private void toggle() {
        if (editToggle == 1) {
            submitBtn.setVisible(true);
            displaynameField.setEditable(true);
            passwordField.setEditable(true);
            confirmField.setEditable(true);
        } else {
            submitBtn.setVisible(false);
            displaynameField.setEditable(false);
            passwordField.setEditable(false);
            confirmField.setEditable(false);
        }
    }
}
