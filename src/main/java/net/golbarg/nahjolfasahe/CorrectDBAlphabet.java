package net.golbarg.nahjolfasahe;

import javafx.collections.ObservableList;
import net.golbarg.nahjolfasahe.models.Category;
import net.golbarg.nahjolfasahe.models.Hadis;

public class CorrectDBAlphabet {
    public static void main(String[] args) {
        ObservableList<Hadis> hadisList = DBController.getAllHadis();

        for(int i = 0; i < hadisList.size(); i++) {
            Hadis hadis = hadisList.get(i);
            updateHadisText(hadis);
            updateCategoryText(hadis);
        }
    }

    private static void updateHadisText(Hadis hadis) {
        hadis.setHadisText(hadis.getHadisText().replaceAll("ي", "ی"));
        DBController.updateHadisText(hadis);
        System.out.println("Updating Hadis of: " + hadis.getId());
    }

    private static void updateCategoryText(Hadis hadis) {
        hadis.setHadisText(hadis.getHadisText().replaceAll("ي", "ی"));
        hadis.getCategory().setTitle(hadis.getCategory().getTitle().replaceAll("ي", "ی"));
        hadis.getSubCategory().setTitle(hadis.getSubCategory().getTitle().replaceAll("ي", "ی"));
        DBController.updateCategory(hadis);
        System.out.println("Updating Hadis of: " + hadis.getId());
    }
}
