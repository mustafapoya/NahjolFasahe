package net.golbarg.nahjolfasahe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.golbarg.nahjolfasahe.convertor.CategoryConvertor;
import net.golbarg.nahjolfasahe.models.Category;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.StatusBar;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private VBox vbTop;

    @FXML
    private SplitPane splitPane;
    // right panel
    @FXML
    private BorderPane borderPaneCategory;
    @FXML
    private TextField txtSearchCategory;
    @FXML
    private ListView<Category> listViewCategory;
    // left panel
    @FXML
    private BorderPane borderPaneContent;
    @FXML
    private TextField txtSearchHadis;
    @FXML
    private TextArea txtHadis;

    @FXML
    private VBox vbBottom;
    @FXML
    private StatusBar statusBar;

    
}
