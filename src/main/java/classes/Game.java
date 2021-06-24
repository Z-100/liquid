package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Game {
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty thumbnail;
    private SimpleDoubleProperty price;

    public Game(String title, String description, String thumbnail, double price) {
        this.title = new SimpleStringProperty(title);
        this.description =  new SimpleStringProperty(description);
        this.thumbnail =  new SimpleStringProperty(thumbnail);
        this.price =  new SimpleDoubleProperty(price);
    }

    public ObservableValue<String> titleProperty() {
        return this.title;
    }
}
