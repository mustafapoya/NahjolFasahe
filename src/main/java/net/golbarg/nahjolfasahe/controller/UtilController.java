package net.golbarg.nahjolfasahe.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.golbarg.nahjolfasahe.HadisImageViewController;
import net.golbarg.nahjolfasahe.MainApp;
import net.golbarg.nahjolfasahe.models.Hadis;
import net.golbarg.nahjolfasahe.models.Position;
import net.golbarg.nahjolfasahe.trans.Persian;
import org.controlsfx.control.Notifications;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UtilController {
    public static final String SAVE_PATH = System.getProperty("user.home") + "/Downloads/";

    public static boolean createHadisImage(Hadis hadis) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hadis-image-view.fxml"));
        BorderPane borderPane = fxmlLoader.load();
        HadisImageViewController controller = fxmlLoader.getController();
        controller.initializeData(hadis);

        WritableImage image = borderPane.snapshot(new SnapshotParameters(), null);

        String fileName = "nahjol_fasahe_" + hadis.getId() + ".png";
        File file = new File(SAVE_PATH + fileName);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void showHadisImageSaveNotification(boolean isImageSaved) {
        Notifications notification = Notifications.create();
        notification.text(isImageSaved ? Persian.HADIS_IMAGE_SAVED : Persian.HADIS_IMAGE_NOT_SAVED);
        notification.graphic(new FontIcon(isImageSaved ? "bi-image" : "bi-x"));
        notification.title(Persian.APP_NAME);
        if(isImageSaved) {
            notification.onAction(event->{
                try {
                    Desktop.getDesktop().open(new File(UtilController.SAVE_PATH));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
        notification.show();
    }

    public static boolean copyToClipboard(String text) {
        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        return systemClipboard.setContent(content);
    }

    public static void showCopyToClipboardNotification() {
        Notifications notification = Notifications.create();
        notification.text(Persian.COPIED_TO_CLIPBOARD);
        notification.graphic(new FontIcon("bi-file-text-fill"));
        notification.title(Persian.APP_NAME);
        notification.show();
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

    public static void setStageLocation(Stage stage) {
        Position position = new Position(Position.CORNER.BOTTOM_RIGHT, stage.getWidth(), stage.getHeight());
        stage.setX(position.getXLocation());
        stage.setY(position.getYLocation());
        System.out.println(Position.getTaskbarHeight2());
    }
}
