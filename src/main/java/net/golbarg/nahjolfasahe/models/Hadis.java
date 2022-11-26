package net.golbarg.nahjolfasahe.models;

public class Hadis {
    private long id;
    private Category category;
    private Category subCategory;
    private String hadisText;

    private boolean bookmark;

    public Hadis(long id, Category category, Category subCategory, String hadisText, boolean bookmark) {
        this.id = id;
        this.category = category;
        this.subCategory = subCategory;
        this.hadisText = hadisText;
        this.bookmark = bookmark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Category subCategory) {
        this.subCategory = subCategory;
    }

    public String getHadisText() {
        return hadisText;
    }

    public void setHadisText(String hadisText) {
        this.hadisText = hadisText;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

    @Override
    public String toString() {
        return "Hadis{" +
                "id=" + id +
                ", category=" + category +
                ", subCategory=" + subCategory +
                ", hadisText='" + hadisText + '\'' +
                ", bookmark=" + bookmark +
                '}';
    }
}
