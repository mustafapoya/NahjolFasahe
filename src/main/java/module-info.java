module net.golbarg.nahjolfasahe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens net.golbarg.nahjolfasahe to javafx.fxml;
    exports net.golbarg.nahjolfasahe;
}