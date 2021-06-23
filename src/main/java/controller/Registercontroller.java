package controller;

import classes.Conn;
import classes.Stages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
            tokencheck(usernameField.getText(), passwordField.getText(), tokenField.getText());
        });

        loginBtn.setOnMouseClicked(mouseEvent -> {
            Stages stages = new Stages(this.primaryStage);
            stages.loginpage();
        });
    }

    private void tokencheck(String username, String password, String token) {
        try {
            String statement1 = "SELECT token FROM token";
            String statement2 = "INSERT INTO user (username, displayname, password) VALUES (" + username + ", " + username + ", " + password + ")";

            Conn conn1 = new Conn();
            conn1.query(statement1);

            while(conn1.getResult().next()) {
                if (token.equals(conn1.getResult().getString("token"))) {
                    Conn conn2 = new Conn();
                    conn2.query(statement2);

                    Stages stages = new Stages(this.primaryStage);
                    stages.storepage();
                } else { // TODO: Maybe Regex
                    check.setText("The entered token is wrong");
                    check.setTextFill(Color.rgb(166, 0, 255, 1));
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}
