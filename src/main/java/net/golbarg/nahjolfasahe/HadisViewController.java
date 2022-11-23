package net.golbarg.nahjolfasahe;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.golbarg.nahjolfasahe.models.Hadis;

public class HadisViewController {
    @FXML
    private VBox root;

    @FXML
    private HBox header;
    //
    @FXML
    private HBox headerLeft;
    @FXML
    private Button btnCopy;
    //
    @FXML
    private HBox headerRight;
    @FXML
    private Label lblHadisNumber;
    @FXML
    private Label lblHadisCategory;
    @FXML
    private Label lblHadisSubCategory;
    //
    @FXML
    private VBox hadisContainer;
    @FXML
    private Text txtHadis;

    public void initializeData(Hadis hadis) {

        lblHadisCategory.setText(hadis.getCategory().getTitle());
        lblHadisSubCategory.setText(hadis.getSubCategory().getTitle());
        lblHadisNumber.setText(String.valueOf(hadis.getId() + 1));
        txtHadis.setText(hadis.getHadisText());

        MainApp.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtHadis.wrappingWidthProperty().setValue(MainApp.stage.getWidth() - 270);
            }
        });
    }
}
