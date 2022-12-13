package net.golbarg.nahjolfasahe.test;

import javafx.collections.ObservableList;
import net.golbarg.nahjolfasahe.DBController;
import net.golbarg.nahjolfasahe.models.Category;

import java.io.File;

public class TestDb {
    public static void main(String[] args) {
        DBController.checkDriver();
        ObservableList<Category> list = DBController.getAllSubCategory("آخرالزمان");

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

//        try {
//            Class.forName("org.sqlite.JDBC");
//            DriverManager.registerDriver(new org.sqlite.JDBC());
//
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + TestDb.class.getResource("hadis_db.db").toExternalForm());
//            String query = "select * from hadises;";
//
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet results = statement.executeQuery();
//
//            System.out.println("سلام بر شما");
//            while(results.next()) {
//                System.out.println(results.getString("category"));
//            }
//
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

    }
}
