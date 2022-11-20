module net.golbarg.nahjolfasahe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.xerial.sqlitejdbc;

    opens net.golbarg.nahjolfasahe to javafx.fxml;
    exports net.golbarg.nahjolfasahe;
}