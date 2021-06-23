package controller;

import classes.Conn;
import classes.Stages;
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

    Stage primaryStage;
    int i;

    public void init(Stage primaryStage) {

        this.primaryStage = primaryStage;

        loginBtn.setOnAction(actionEvent -> {
            logincheck(usernameField.getText(), passwordField.getText());
        });

        logoBtn.setOnAction(actionEvent -> {
            i++; // My sisters idea
            if (i > 5) this.primaryStage.setTitle(15 - i + " clicks away");
            if (i >= 15) {
                easteregg.setVisible(true);
                this.primaryStage.setTitle("Easteregg unlocked!");
            }
        });

        registerbtn.setOnMouseClicked(mouseEvent -> {
            Stages stages = new Stages(this.primaryStage);
            stages.registerpage();
        });
    }

    private void logincheck(String accountname, String password) {
        try {
            String statement = "SELECT username, password FROM user";

            Conn conn = new Conn();
            conn.connect(statement);

            while (conn.getResult().next()) {
                String accountnameDB = conn.getResult().getString("username");
                String passwordDB = conn.getResult().getString("password");

                if (accountname.equals(accountnameDB) && password.equals(passwordDB)) {
                    Stages stages = new Stages(this.primaryStage);
                    stages.storepage();
                } else {
                    check.setText("Your account name or password is wrong");
                    check.setTextFill(Color.rgb(166, 0, 255, 1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
