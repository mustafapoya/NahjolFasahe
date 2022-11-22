package net.golbarg.nahjolfasahe;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.golbarg.nahjolfasahe.models.Category;
import net.golbarg.nahjolfasahe.models.Hadis;
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
        statusBar.setText("Loading Categories");
        allCategories = DBController.getAllCategory();
        FilteredList<Category> filteredList= new FilteredList<>(allCategories, data -> true);

        listViewCategory.setItems(filteredList);
        statusBar.setText("Ok.");
        listViewCategory.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                final Label leadLbl = new Label();
                final ListCell<Category> cell = new ListCell<Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item != null) {
                            leadLbl.setText(item.getTitle());
                            setText(item.getTitle());
                        } else {
                            leadLbl.setText("");
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });

        txtSearchCategory.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredList.setPredicate(data -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String search = newValue.toLowerCase();

                    if(data.getTitle().trim().contains(search)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            }
        });

        listViewCategory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {
            @Override
            public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
                txtHadis.setText("");
                statusBar.setText("Loading Hadis");
                statusBar.setProgress(0.0);

                try {
                    ObservableList<Hadis> hadisList = DBController.getHadisOf(newValue.getTitle());
                    int count = 0;
                    for(Hadis hadis: hadisList) {
                        txtHadis.appendText(hadis.getHadisText().trim() + "\n\n\n");
                        
                        if (hadisList.size() > 0){
                            statusBar.setProgress(count / hadisList.size());
                        }
                    }
                    statusBar.setProgress(1.0);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                txtHadis.setScrollTop(Double.MAX_VALUE);
            }
        });

    }
}
