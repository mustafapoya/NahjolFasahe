package net.golbarg.nahjolfasahe;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.golbarg.nahjolfasahe.models.Position;
import net.golbarg.nahjolfasahe.trans.Persian;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MainApp extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        stage.getIcons().add(new Image(getClass().getResourceAsStream("app_icon.png")));

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setMinWidth(740);
        stage.setTitle(Persian.APP_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}