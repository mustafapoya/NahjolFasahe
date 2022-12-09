package net.golbarg.nahjolfasahe;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import net.golbarg.nahjolfasahe.models.Hadis;


public class HadisImageViewController {
    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane container;
    @FXML
    private ImageView imgHadisContainer;
    @FXML
    private Text txtHadisNumber;
    @FXML
    private Text txtHadisText;

    public void initializeData(Hadis hadis) {
        txtHadisNumber.setText(String.valueOf(hadis.getId()));
        txtHadisText.setText(hadis.getHadisText());
    }

}
