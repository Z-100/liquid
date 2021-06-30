package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.io.InputStream;

public class Game {
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty category;
    private SimpleStringProperty type;
    private SimpleDoubleProperty price;
//    private byte[] thumbnail;
    private InputStream thumbnail;

    public Game(String title, String description, String category, String type, double price, InputStream thumbnail) {
        this.title = new SimpleStringProperty(title);
        this.description =  new SimpleStringProperty(description);
        this.category =  new SimpleStringProperty(category);
        this.type = new SimpleStringProperty(type);
        this.price =  new SimpleDoubleProperty(price);
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title.get();
    }
    public String getDescription() {
        return description.get();
    }
    public String getCategory() {
        return category.get();
    }
    public String getType() {
        return type.get();
    }
    public double getPrice() {
        return price.get();
    }
    public InputStream getThumbnail() {
        return thumbnail;
    }
}
