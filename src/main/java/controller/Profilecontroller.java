package controller;

import classes.Conn;
import classes.Stages;
import classes.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.SQLException;

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
    @FXML
    private Label usernameLabel;
    @FXML
    private Label displaynameLabel;
    @FXML
    private Label displaynameLabelTop;

    int editToggle = 0;
    String session_username = UserSession.getUserName();
    String session_displayname = UserSession.getDisplayName();

    //TODO max characters pw + usrname

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

        usernameLabel.setText(session_username);
        displaynameLabel.setText(session_displayname);
        displaynameLabelTop.setText(session_displayname);

        displaynameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 26) {
                displaynameField.setText(oldValue);
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

        submitBtn.setOnAction(actionEvent -> {
            String displayname = displaynameField.getText();
            String password = passwordField.getText();
            String confirm = confirmField.getText();

            if (!displayname.isEmpty() && !password.isEmpty() && !confirm.isEmpty()) {
                changeDetails(displayname, password, confirm, stages);
            } else {
                errorLabel.setText("Please fill in all the information needed");
            }
        });
    }

    private void changeDetails(String displayname, String password, String confirm, Stages stages) {
        Conn conn = new Conn();
        String statement1 = String.format("SELECT password FROM user WHERE username = '%s'", session_username);
        String statement2 = "UPDATE user SET displayname = '" + displayname + "', password = '" + password + "' WHERE username = '" + session_username + "'";

        conn.query(statement1, 0);
        try {
            while (conn.getResult().next()) {
                if (confirm.equals(conn.getResult().getString("password"))) {
                    conn.query(statement2, 1);
                    System.out.println("Correct password");
                    UserSession.setDisplayName(displayname);
                    stages.profilepage();
                } else {
                    errorLabel.setText("Wrong (old) password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in SQL");
        }
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
