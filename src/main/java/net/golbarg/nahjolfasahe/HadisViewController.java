package net.golbarg.nahjolfasahe;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import net.golbarg.nahjolfasahe.models.Hadis;
import org.controlsfx.control.Notifications;
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
        lblHadisNumber.setText(String.valueOf("حدیث شماره: " + hadis.getId()));
        txtHadis.setText(hadis.getHadisText().trim());

        if(hadis.isBookmark()) {
            btnBookmark.setGraphic(getBookmarkFillIcon());
        } else {
            btnBookmark.setGraphic(getBookmarkIcon());
        }

        MainApp.stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtHadis.wrappingWidthProperty().setValue(MainApp.stage.getWidth() - 270);
            }
        });

        txtHadis.wrappingWidthProperty().setValue(MainApp.stage.getWidth() - 270);

        btnCopy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(txtHadis.getText().trim());
                clipboard.setContent(content);

                Notifications notification = Notifications.create();
                notification.text("Copied to Clipboard");
                notification.graphic(new FontIcon("bi-file-text-fill"));
                notification.title("Nahjol Fasahe");
                notification.show();
            }
        });

        btnBookmark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FontIcon icon = (FontIcon) btnBookmark.getGraphic();
                String current_icon = icon.getIconLiteral();
                if(current_icon.equals("bi-journal-bookmark")) {
                    btnBookmark.setGraphic(getBookmarkFillIcon());
                    DBController.toggle_bookmark(hadis, true);
                } else {
                    btnBookmark.setGraphic(getBookmarkIcon());
                    DBController.toggle_bookmark(hadis, false);
                }
            }
        });

    }

    public static FontIcon getBookmarkFillIcon() {
        FontIcon icon = new FontIcon("bi-journal-bookmark-fill");
        icon.setIconSize(15);
        return icon;
    }

    public static FontIcon getBookmarkIcon() {
        FontIcon icon = new FontIcon("bi-journal-bookmark");
        icon.setIconSize(15);
        return icon;
    }
}
