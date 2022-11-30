package net.golbarg.nahjolfasahe;

import javafx.application.Preloader;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import net.golbarg.nahjolfasahe.models.Category;
import net.golbarg.nahjolfasahe.models.Hadis;
import net.golbarg.nahjolfasahe.trans.Persian;
import org.controlsfx.control.StatusBar;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainViewController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private VBox vbTop;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Button btnBookmarks;
    @FXML
    private Button btnDailyHadis;

    @FXML
    private SplitPane splitPane;
    // right panel
    @FXML
    private BorderPane borderPaneCategory;
    @FXML
    private TextField txtSearchCategory;
    @FXML
    private ToggleButton toggleCategory;
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
    FilteredList<Category> filteredList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusBar.setText(Persian.LOADING_CATEGORY);
        allCategories = DBController.getAllCategory();
        filteredList = new FilteredList<>(allCategories, data -> true);

        listViewCategory.setItems(filteredList);
        statusBar.setText(Persian.OK);
        listViewCategory.setCellFactory(createCategoryListViewCellFactory());

        txtSearchCategory.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterCategoryList(newValue, filteredList);
            }
        });

        toggleCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(toggleCategory.isSelected()) {
                    ObservableList<Category> list = filteredList.stream().sorted(Comparator.comparing(Category::getTitle))
                                                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                    statusBar.setText(Persian.SORTED_CATEGORY);
                    listViewCategory.setItems(list);
                } else {
                    statusBar.setText(Persian.SORTED_DEFAULT_CATEGORY);
                    listViewCategory.setItems(filteredList);

                }
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
                        statusBar.setText(Persian.FIRST_TYPE_TO_SEARCH);
                        statusBar.setStyle("-fx-text-fill: red;");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        btnSearchHadis.setOnAction(searchHadisEvent);
        txtSearchHadis.setOnAction(searchHadisEvent);

        btnBookmarks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    listViewCategory.getSelectionModel().clearSelection();
                    ObservableList<Hadis> hadisList = DBController.getBookmarkedHadises();
                    displayHadis(hadisList);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnDailyHadis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("daily-hadis-view.fxml"));
                    BorderPane element = fxmlLoader.load();
                    Scene dailyHadisScene = new Scene(element);
                    dailyHadisScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

                    Stage dailyHadisStage = new Stage();
                    dailyHadisStage.setTitle(Persian.APP_NAME);
                    dailyHadisStage.setScene(dailyHadisScene);
                    dailyHadisStage.initOwner(MainApp.stage);
                    dailyHadisStage.setAlwaysOnTop(true);
                    dailyHadisStage.requestFocus();
                    dailyHadisStage.setResizable(false);
                    dailyHadisStage.initStyle(StageStyle.DECORATED);
                    dailyHadisStage.showAndWait();

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    private static Callback<ListView<Category>, ListCell<Category>> createCategoryListViewCellFactory() {
        return new Callback<ListView<Category>, ListCell<Category>>() {
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
        };
    }

    private static void filterCategoryList(String newValue, FilteredList<Category> filteredList) {
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
