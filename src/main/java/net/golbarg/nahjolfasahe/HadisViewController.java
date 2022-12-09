package net.golbarg.nahjolfasahe;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.golbarg.nahjolfasahe.controller.UtilController;
import net.golbarg.nahjolfasahe.models.Hadis;
import net.golbarg.nahjolfasahe.trans.Persian;
import org.kordamp.ikonli.javafx.FontIcon;

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
    @FXML
    private Button btnBookmark;
    @FXML
    private Button btnSaveImage;
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
        lblHadisNumber.setText(Persian.HADIS_NUMBER + hadis.getId());
        txtHadis.setText(hadis.getHadisText().trim());

        if(hadis.isBookmark()) {
            btnBookmark.setGraphic(UtilController.getBookmarkFillIcon());
        } else {
            btnBookmark.setGraphic(UtilController.getBookmarkIcon());
        }

        MainApp.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtHadis.wrappingWidthProperty().setValue(MainApp.stage.getWidth() - 270);
            }
        });

        txtHadis.wrappingWidthProperty().setValue(MainApp.stage.getWidth() - 270);

        btnCopy.setOnAction(event->{
            boolean result = UtilController.copyToClipboard(txtHadis.getText().trim());
            if(result) {
                UtilController.showCopyToClipboardNotification();
            }
        });

        btnBookmark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FontIcon icon = (FontIcon) btnBookmark.getGraphic();
                String current_icon = icon.getIconLiteral();
                if(current_icon.equals("bi-journal-bookmark")) {
                    btnBookmark.setGraphic(UtilController.getBookmarkFillIcon());
                    DBController.toggle_bookmark(hadis, true);
                } else {
                    btnBookmark.setGraphic(UtilController.getBookmarkIcon());
                    DBController.toggle_bookmark(hadis, false);
                }
            }
        });

        btnSaveImage.setOnAction(event -> {
            new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        boolean result = UtilController.createHadisImage(hadis);
                        UtilController.showHadisImageSaveNotification(result);
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }, "Thread Save Hadis Image").start();

        });
    }

}
