package net.golbarg.nahjolfasahe;

import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import net.golbarg.nahjolfasahe.controller.UtilController;
import net.golbarg.nahjolfasahe.models.Category;
import net.golbarg.nahjolfasahe.models.Hadis;
import net.golbarg.nahjolfasahe.trans.Persian;
import org.controlsfx.control.StatusBar;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
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
    private Button btnInfo;

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
    private Stage parentStage;
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
                        statusBar.setText(Persian.LOADING + ": " + newValue.getTitle());
                        ObservableList<Hadis> hadisList = DBController.getHadisOf(newValue.getTitle());
                        displayHadis(hadisList);
                        statusBar.setText(Persian.DISPLAY_HADIS_OF + ": " + newValue.getTitle() + "(" + hadisList.size()  + " " + Persian.HADIS + ")");
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
                        statusBar.setText(Persian.SEARCHING_FOR + searchText);
                        ObservableList<Hadis> hadisList = DBController.searchHadisOf(searchText);
                        displayHadis(hadisList);
                        statusBar.setText(Persian.FINDED_HADISES + ": " + searchText + " (" + hadisList.size() + " " + Persian.HADIS + ")");

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
                    statusBar.setText(Persian.LOADING_BOOKMARKED_HADISES);
                    listViewCategory.getSelectionModel().clearSelection();
                    ObservableList<Hadis> hadisList = DBController.getBookmarkedHadises();
                    displayHadis(hadisList);
                    statusBar.setText(Persian.DISPLAYED_BOOKMARKED_HADISES);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnDailyHadis.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    statusBar.setText(Persian.LOADING);
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hadis-daily-view.fxml"));
                    BorderPane element = fxmlLoader.load();
                    Scene dailyHadisScene = new Scene(element);
                    dailyHadisScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

                    Stage dailyHadisStage = new Stage();
                    dailyHadisStage.getIcons().add(new Image(getClass().getResourceAsStream("app_icon.png")));
                    dailyHadisStage.setTitle(Persian.APP_NAME);
                    dailyHadisStage.setScene(dailyHadisScene);
                    if(parentStage != null) {
                        dailyHadisStage.initOwner(parentStage);
                    }
                    dailyHadisStage.initModality(Modality.WINDOW_MODAL);
                    dailyHadisStage.setAlwaysOnTop(true);
                    dailyHadisStage.requestFocus();
                    dailyHadisStage.setResizable(false);
                    dailyHadisStage.initStyle(StageStyle.DECORATED);
                    UtilController.displayAtCenterOf(parentStage, dailyHadisStage);
                    dailyHadisStage.showAndWait();
                    statusBar.setText(Persian.DISPLAYED_DAILY_HADIS);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnInfo.setOnAction(event -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("about-view.fxml"));
                BorderPane element = fxmlLoader.load();
                Scene aboutScene = new Scene(element);
                aboutScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

                Stage aboutSage = new Stage();
                aboutSage.getIcons().add(new Image(getClass().getResourceAsStream("app_icon.png")));
                aboutSage.setTitle(Persian.APP_NAME);
                aboutSage.setScene(aboutScene);
                if(parentStage != null) {
                    aboutSage.initOwner(parentStage);
                }
                aboutSage.initModality(Modality.WINDOW_MODAL);
                aboutSage.setAlwaysOnTop(true);
                aboutSage.requestFocus();
                aboutSage.setResizable(false);
                aboutSage.initStyle(StageStyle.DECORATED);
                UtilController.displayAtCenterOf(parentStage, aboutSage);
                aboutSage.showAndWait();
            } catch(Exception ex) {
                ex.printStackTrace();
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
        new Thread(() -> {
            try {
                for(Hadis hadis: hadisList) {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hadis-view.fxml"));
                    VBox element = fxmlLoader.load();
                    HadisViewController controller = fxmlLoader.getController();
                    controller.initializeData(hadis, parentStage);
                    Platform.runLater(() -> hadisContainer.getChildren().add(element));
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }, "Thread Display Hadis").start();
    }

    public void setParentStage(Stage stage) {
        this.parentStage = stage;
    }
}
