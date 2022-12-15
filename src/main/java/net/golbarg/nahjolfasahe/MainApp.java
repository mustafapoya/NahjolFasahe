package net.golbarg.nahjolfasahe;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.golbarg.nahjolfasahe.controller.UtilController;
import net.golbarg.nahjolfasahe.trans.Persian;
import java.io.IOException;

public class MainApp extends Application {

    public static Stage stage;
    public static HostServices hostServices;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.hostServices = getHostServices();

        stage.getIcons().add(new Image(getClass().getResourceAsStream("app_icon.png")));

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("splash-screen-view.fxml"));
        BorderPane root = fxmlLoader.load();
        SplashScreenViewController controller = fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle(Persian.APP_NAME);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        UtilController.displayAtCenter(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }


}