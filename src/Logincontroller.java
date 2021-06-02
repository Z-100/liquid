import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Logincontroller
{
    // @FXML
    @FXML
    private TextField accountnameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Label check;

    // variables
    String accountname;
    String password;

    public void init(Stage stage) {

        loginBtn.setOnAction(actionEvent -> {
            this.accountname = accountnameField.getText();
            this.password = passwordField.getText();
            logincheck(this.accountname, this.password);
        });
    }

    private void logincheck(String accountname, String password) {
        Conn.conn();
        if (this.accountname.equals("marvin") && this.password.equals("sananas")) {
            check.setText("sfsgs");
        } else {
            check.setText("Your account name or password is wrong");
        }
    }
}
