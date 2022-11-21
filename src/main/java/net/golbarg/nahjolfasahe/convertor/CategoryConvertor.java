package net.golbarg.nahjolfasahe.convertor;

import javafx.util.StringConverter;
import net.golbarg.nahjolfasahe.models.Category;

public class CategoryConvertor extends StringConverter<Category> {
    @Override
    public Category fromString(String string) {
        return new Category(string);
    }

    @Override
    public String toString(Category object) {
        return object.getTitle();
    }
}
