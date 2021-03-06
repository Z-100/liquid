package controller;

import classes.Conn;
import classes.Stages;
import classes.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Logincontroller {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button logoBtn;
    @FXML
    private Label check;
    @FXML
    private ImageView easteregg;
    @FXML
    private Label registerbtn;

    private Stage primaryStage;
    private int i;
    private int j = 0;

    public void init(Stage primaryStage) {

        this.primaryStage = primaryStage;
        usernameField.requestFocus();

        loginBtn.setOnAction(actionEvent -> {
            this.j++;
            if (this.j >= 5) Platform.exit();

            logincheck(usernameField.getText(), passwordField.getText());
        });

        logoBtn.setOnAction(actionEvent -> {
            i++; // My sisters idea
            if (i > 5) this.primaryStage.setTitle(15 - i + " clicks away");
            if (i >= 15) {
                easteregg.setVisible(true);
                this.primaryStage.setTitle("Easter egg unlocked!");
            }
        });

        registerbtn.setOnMouseClicked(mouseEvent -> {
            Stages stages = new Stages(this.primaryStage);
            stages.registerpage();
        });
    }

    private void logincheck(String username, String password) {
        try {
            String statement = String.format(
                    "SELECT username, displayname, password " +
                        "FROM user"
            );

            Conn conn = new Conn();
            conn.query(statement, 0);

            while (conn.getResult().next()) {
                String usernameDB = conn.getResult().getString("username");
                String displayNameDB = conn.getResult().getString("displayname");
                String passwordDB = conn.getResult().getString("password");

                if (username.equals(usernameDB) && password.equals(passwordDB)) {
                    UserSession.getInstance(usernameDB, displayNameDB);
                    Stages stages = new Stages(this.primaryStage);
                    stages.storepage();
                } else {
                    check.setText("Your account name or password is wrong");
                    check.setTextFill(Color.rgb(166, 0, 255, 1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in SQL");
        }
    }
}
