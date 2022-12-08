package net.golbarg.nahjolfasahe;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.golbarg.nahjolfasahe.models.Position;
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

//        Position position = new Position(Position.CORNER.BOTTOM_RIGHT, stage.getWidth(), stage.getHeight());
//        stage.setX(position.getXLocation());
//        stage.setY(position.getYLocation());

        System.out.println(getTaskbarHeight());
    }

    public static void main(String[] args) {
        launch();
    }

    public static double getTaskbarHeight() {
        final var screen = Screen.getPrimary();

        final var screenVbWidth = screen.getVisualBounds().getWidth();
        final var screenVbHeight = screen.getVisualBounds().getHeight();

        final var screenWidth = screen.getBounds() .getWidth();
        final var screenHeight = screen.getBounds() .getHeight();

        System.out.println("Screen Visual Bounds......... w*h.: " + screenVbWidth + '*' + screenVbHeight);
        System.out.println("Screen Bounds................ w*h.: " + screenWidth + '*' + screenHeight);
        return (screenHeight - screenVbHeight);
    }
}