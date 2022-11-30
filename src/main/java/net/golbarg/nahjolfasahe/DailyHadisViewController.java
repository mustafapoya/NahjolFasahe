package net.golbarg.nahjolfasahe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.golbarg.nahjolfasahe.models.Category;
import net.golbarg.nahjolfasahe.models.Hadis;
import net.golbarg.nahjolfasahe.trans.Persian;
import org.controlsfx.control.Notifications;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class DailyHadisViewController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private VBox vbHeader;
    @FXML
    private HBox hbHeader;
    @FXML
    private HBox hbHeaderLeft;
    @FXML
    private Button btnBookmark;
    @FXML
    private Button btnCopy;

    @FXML
    private HBox hbHeaderRight;
    @FXML
    private Label lblHadisCategory;

    @FXML
    private ScrollPane spContent;
    @FXML
    private VBox vbContent;
    @FXML
    private Text txtHadis;

    @FXML
    private VBox vbFooter;
    @FXML
    private HBox hbFooter;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;

    private Hadis currentHadis = null;
    ObservableList<Hadis> hadisList = FXCollections.observableArrayList();
    int current_index = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current_index = 0;
        hadisList = DBController.getAllHadis();

        displayHadis();

        btnNext.setOnAction(actionEvent -> {
            current_index++;
            if(current_index > hadisList.size()) {
                current_index = hadisList.size() - 1;
            }
            displayHadis();
        });

        btnPrevious.setOnAction(actionEvent -> {
            current_index--;
            if(current_index < 0) {
                current_index = 0;
            }
            displayHadis();
        });

        btnCopy.setOnAction(actionEvent -> {
            Clipboard systemClipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(txtHadis.getText());
            systemClipboard.setContent(content);

            Notifications notification = Notifications.create();
            notification.text(Persian.COPIED_TO_CLIPBOARD);
            notification.graphic(new FontIcon("bi-file-text-fill"));
            notification.title(Persian.APP_NAME);
            notification.show();
        });

        btnBookmark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateBookmarkIcon();
            }
        });

    }

    private void displayHadis() {
        if(current_index < hadisList.size() && current_index >= 0) {
            currentHadis = hadisList.get(current_index);
            txtHadis.setText(currentHadis.getHadisText().trim());
            lblHadisCategory.setText(currentHadis.getCategory().getTitle());
            checkIcon();
        }
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

    private void updateBookmarkIcon() {
        FontIcon icon = (FontIcon) btnBookmark.getGraphic();
        String current_icon = icon.getIconLiteral();

        if(current_icon.equals("bi-journal-bookmark")) {
            btnBookmark.setGraphic(getBookmarkFillIcon());
            DBController.toggle_bookmark(hadisList.get(current_index), true);
            hadisList.get(current_index).setBookmark(true);
        } else {
            btnBookmark.setGraphic(getBookmarkIcon());
            DBController.toggle_bookmark(hadisList.get(current_index), false);
            hadisList.get(current_index).setBookmark(false);
        }
    }

    private void checkIcon() {
        if(currentHadis.isBookmark()) {
            btnBookmark.setGraphic(getBookmarkFillIcon());
        } else {
            btnBookmark.setGraphic(getBookmarkIcon());
        }
    }
}
