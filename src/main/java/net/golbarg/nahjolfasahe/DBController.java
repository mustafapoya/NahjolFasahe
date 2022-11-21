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

    public static ObservableList<Hadis> getAllHadis() {
        ObservableList<Hadis> hadisList = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TABLE_HADIS;

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                long id = result.getLong("id");
                Category category = new Category(result.getString("category"));
                Category subCategory = new Category(result.getString("sub_category"));
                String hadisText = result.getString("hadis_text");

                hadisList.add(new Hadis(id, category, subCategory, hadisText));


            }
        } catch(SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Hadises from database ");
        }

        return hadisList;
    }

    public static ObservableList<Hadis> getHadisOf(String category) {
        ObservableList<Hadis> hadisList = FXCollections.observableArrayList();
        String query = "SELECT * FROM " + TABLE_HADIS + " WHERE category = ?;";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, category);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                long id = result.getLong("id");
                Category category1 = new Category(result.getString("category"));
                Category subCategory = new Category(result.getString("sub_category"));
                String hadisText = result.getString("hadis_text");

                hadisList.add(new Hadis(id, category1, subCategory, hadisText));

            }
        } catch(SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Hadises by category from database");
        }

        return hadisList;
    }

    public static ObservableList<Category> getAllCategory() {
        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        String query = "select distinct category from  " + TABLE_HADIS;

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Category category = new Category(result.getString("category"));
                categoryList.add(category);
            }
        } catch(SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Hadis Category from database ");
        }

        return categoryList;
    }


    public static ObservableList<Category> getAllSubCategory(String category) {
        ObservableList<Category> subCategoryList = FXCollections.observableArrayList();
        String query = "select distinct sub_category from "+ TABLE_HADIS +" where category = ?;";

        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, category);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                subCategoryList.add(new Category(result.getString("sub_category")));
            }
        } catch(SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load Hadis Category from database ");
        }

        return subCategoryList;
    }

}