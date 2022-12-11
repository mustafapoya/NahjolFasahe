package net.golbarg.nahjolfasahe;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.golbarg.nahjolfasahe.trans.Persian;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenViewController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private ImageView imgBackground;
    @FXML
    private ProgressBar progressBar;

    private Stage parentStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            for(int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(10);
                    int finalI = i;
                    Platform.runLater(() -> {
                        progressBar.setProgress(finalI / 100.0);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Thread Save Hadis Image").start();

        progressBar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(t1.equals(1.0)) {
                    displayMainView();
                    closeSplash();
                }
            }
        });
    }

    private void displayMainView() {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("app_icon.png")));
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
            BorderPane root = fxmlLoader.load();
            MainViewController controller = fxmlLoader.getController();
            controller.setStage(stage);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            stage.setMinWidth(740);
            stage.setTitle(Persian.APP_NAME);
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.parentStage = stage;
    }

    private void closeSplash() {
        if(this.parentStage != null) {
            this.parentStage.close();
        }
    }
}
