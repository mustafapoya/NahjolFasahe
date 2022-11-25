package net.golbarg.nahjolfasahe;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.golbarg.nahjolfasahe.models.Category;
import net.golbarg.nahjolfasahe.models.Hadis;
import org.controlsfx.control.StatusBar;
import org.controlsfx.control.action.Action;

import java.io.IOException;
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
    private Button btnSearchHadis;
    @FXML
    private ScrollPane scrollPaneHadis;
    @FXML
    private VBox hadisContainer;

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

                try {
                    if(newValue != null) {
                        ObservableList<Hadis> hadisList = DBController.getHadisOf(newValue.getTitle());
                        displayHadis(hadisList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        EventHandler<ActionEvent> searchHadisEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String searchText = txtSearchHadis.getText();
                    if(searchText.length() > 0) {
                        ObservableList<Hadis> hadisList = DBController.searchHadisOf(searchText);
                        displayHadis(hadisList);
                    } else {
                        statusBar.setText("برای جستجو متنی را بنویسید.");
                        statusBar.setStyle("-fx-text-fill: red;");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        btnSearchHadis.setOnAction(searchHadisEvent);
        txtSearchHadis.setOnAction(searchHadisEvent);
    }

    private void displayHadis(ObservableList<Hadis> hadisList) throws IOException {
        hadisContainer.getChildren().clear();
        for(Hadis hadis: hadisList) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hadis-view.fxml"));
            VBox element = fxmlLoader.load();
            HadisViewController controller = fxmlLoader.getController();
            controller.initializeData(hadis);
            hadisContainer.getChildren().add(element);
        }
    }
}
