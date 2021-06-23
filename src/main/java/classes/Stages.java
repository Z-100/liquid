package classes;

import controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Stages {

    Stage primaryStage;

    public Stages(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void loginpage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Loginpage.fxml"));
            Parent root = fxmlLoader.load();
            Logincontroller controller = fxmlLoader.getController();
            controller.init(this.primaryStage);

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.setResizable(false);
            this.primaryStage.setTitle("L I Q U I D: Login");
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerpage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Registerpage.fxml"));
            Parent root = fxmlLoader.load();
            Registercontroller registercontroller = fxmlLoader.getController();
            registercontroller.init(this.primaryStage);

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.setResizable(false);
            this.primaryStage.setTitle("L I Q U I D: Register");
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storepage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Storepage.fxml"));
            Parent root = fxmlLoader.load();
            Storecontroller storecontroller = fxmlLoader.getController();
            storecontroller.init(this.primaryStage);

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.setResizable(false);
            this.primaryStage.setTitle("L I Q U I D: Store");
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void librarypage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Librarypage.fxml"));
            Parent root = fxmlLoader.load();
            Librarycontroller librarycontroller = fxmlLoader.getController();
            librarycontroller.init(this.primaryStage);

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.setResizable(false);
            this.primaryStage.setTitle("L I Q U I D: Library");
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void profilepage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Profilepage.fxml"));
            Parent root = fxmlLoader.load();
            Profilecontroller profilecontroller = fxmlLoader.getController();
            profilecontroller.init(this.primaryStage);

            this.primaryStage.setScene(new Scene(root));
            this.primaryStage.setResizable(false);
            this.primaryStage.setTitle("L I Q U I D: Profile");
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
