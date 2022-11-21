package net.golbarg.nahjolfasahe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.golbarg.nahjolfasahe.models.Category;
import net.golbarg.nahjolfasahe.models.Hadis;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBController {
    public static final String DB_PATH = DBController.class.getResource("hadis_db.db").toString();
    private static final String TABLE_HADIS = "hadises";

    public static boolean checkDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch(ClassNotFoundException | SQLException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQLite Drivers");
            return false;
        }
    }

    public static Connection getConnection() {
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;

        try {
            connection = DriverManager.getConnection(dbPrefix + DB_PATH);
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not connect to SQLite DB at " + DB_PATH);
            return null;
        }

        return connection;
    }

    

}
