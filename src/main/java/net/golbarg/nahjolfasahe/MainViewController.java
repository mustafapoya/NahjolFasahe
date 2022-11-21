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

    ObservableList<Category> allCategories = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allCategories = DBController.getAllCategory();
        listViewCategory.setItems(allCategories);
        listViewCategory.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip = new Tooltip();
                final ListCell<Category> cell = new ListCell<Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            leadLbl.setText(item.getTitle());
                            setText(item.getTitle());
                            tooltip.setText(item.getTitle());
                            setTooltip(tooltip);
                        } else {

                        }
                    }
                };
                return cell;
            }
        });
    }
}
