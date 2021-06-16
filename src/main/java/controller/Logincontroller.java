package controller;

import classes.Conn;
import classes.Stages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Logincontroller {

    @FXML
    private TextField accountnameField;
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

    String accountname;
    String password;
    Stage primaryStage;
    int i;

    public void init(Stage primaryStage) {

        this.primaryStage = primaryStage;

        loginBtn.setOnAction(actionEvent -> {
            this.accountname = accountnameField.getText();
            this.password = passwordField.getText();
            logincheck(this.accountname, this.password);
        });

        logoBtn.setOnAction(actionEvent -> {
            i++; // My sisters idea
            if (i > 5) this.primaryStage.setTitle(15 - i + " clicks away");
            if (i >= 15) {
                easteregg.setVisible(true);
                this.primaryStage.setTitle("Easteregg unlocked!");
            }
        });
    }

    private void logincheck(String accountname, String password) {
        Conn.conn();
        if (this.accountname.equals("marvin") && this.password.equals("sananas")) {
            Stages stages = new Stages(this.primaryStage);
            stages.storepage();
        } else {
            check.setText("Your account name or password is wrong");
        }
    }
}
