package net.golbarg.nahjolfasahe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDb {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());

            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + TestDb.class.getResource("hadis_db.db").toExternalForm());
            String query = "select * from hadises;";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet results = statement.executeQuery();

            System.out.println("سلام بر شما");
            while(results.next()) {
                System.out.println(results.getString("category"));
            }


        } catch(Exception e) {
            e.printStackTrace();
        }


    }
}
