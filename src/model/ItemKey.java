package model;

import java.util.Objects;

public class ItemKey {
    private String brand;
    private String category;

    public ItemKey(String brand, String category) {
        this.brand = brand;
        this.category = category;
    }

    public String getBrand() { return this.brand; }
    public String getCategory() { return this.category; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemKey)) return false;
        ItemKey key = (ItemKey) o;
        return Objects.equals(brand, key.brand) && Objects.equals(category, key.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, category);
    }

    @Override
    public String toString() {
        return brand + ", " + category;
    }
}