package controller;

import classes.Conn;
import classes.Stages;
import classes.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Registercontroller {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField tokenField;
    @FXML
    private Button registerBtn;
    @FXML
    private Label loginBtn;
    @FXML
    private Label check;

    Stage primaryStage;

    public void init(Stage primaryStage) {
        this.primaryStage = primaryStage;

        registerBtn.setOnAction(actionEvent -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String token = tokenField.getText();

            if (!username.isEmpty() && !password.isEmpty() && !token.isEmpty()) {
                tokencheck(username, password, token);
            } else {
                check.setText("Please fill in all the information needed");
            }
        });

        loginBtn.setOnMouseClicked(mouseEvent -> {
            Stages stages = new Stages(this.primaryStage);
            stages.loginpage();
        });
    }

    private void tokencheck(String username, String password, String token) {
        try {
            String statement1 = "SELECT token FROM token";
            String statement2 = "INSERT INTO user (username, displayname, password) VALUES ('" +username + "', '" +username + "', '" + password  + "')";

            Conn conn = new Conn();
            conn.query(statement1, 0);

            while(conn.getResult().next()) {
                if (token.equals(conn.getResult().getString("token"))) {
                    conn.query(statement2, 1);
                    UserSession.getInstance(username, username);
                    Stages stages = new Stages(this.primaryStage);
                    stages.storepage();
                } else {
                    check.setText("The entered token is wrong");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
