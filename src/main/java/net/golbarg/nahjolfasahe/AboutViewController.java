package net.golbarg.nahjolfasahe;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private VBox vbCenter;
    @FXML
    private HBox hbAppTitle;
    @FXML
    private Label lblAppName;

    @FXML
    private ScrollPane spContent;
    @FXML
    private VBox vbContent;
    @FXML
    private Text txtBesmellah;
    @FXML
    private Text txtDescription;

    @FXML
    private HBox hbBottom;
    @FXML
    private HBox hbBottomLeft;
    @FXML
    private HBox hbBottomRight;
    @FXML
    private Button btnWebsite;
    @FXML
    private Button btnYoutube;
    @FXML
    private Button btnGithub;
    @FXML
    private Button btnLinkedin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnWebsite.setOnAction(event -> {
            openURL("https://golbarg.net");
        });

        btnYoutube.setOnAction(event-> {
            openURL("https://www.youtube.com/@golbargnet");
        });

        btnGithub.setOnAction(event -> {
            openURL("https://github.com/mustafapoya/NahjolFasahe");
        });

        btnLinkedin.setOnAction(event -> {
            openURL("https://www.linkedin.com/company/golbargnet/");
        });
    }

    private void openURL(String url) {
        try {
            MainApp.hostServices.showDocument(url);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
