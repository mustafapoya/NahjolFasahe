package net.golbarg.nahjolfasahe;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.golbarg.nahjolfasahe.trans.Persian;

import java.io.IOException;

public class MainApp extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        stage.getIcons().add(new Image(getClass().getResourceAsStream("app_icon.png")));
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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