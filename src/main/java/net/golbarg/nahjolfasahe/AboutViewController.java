package net.golbarg.nahjolfasahe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private VBox vbContent;
    @FXML
    private HBox hbAppTitle;
    @FXML
    private Label lblAppName;

    @FXML
    private ScrollPane spContent;
    @FXML
    private VBox vbSpContent;
    @FXML
    private Text txtBesmellah;
    @FXML
    private Text txtDescription;

    @FXML
    private HBox bottom;
    @FXML
    private HBox bottomLeft;
    @FXML
    private HBox bottomRight;
    @FXML
    private Button btnWebsite;
    @FXML
    private Button btnFacebook;
    @FXML
    private Button btnGithub;
    @FXML
    private Button btnLinkedin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
