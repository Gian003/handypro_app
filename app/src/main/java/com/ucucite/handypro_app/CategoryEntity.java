package com.ucucite.handypro_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "categories")
public class CategoryEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int image;
    public String label;

    public CategoryEntity(int image, String label) {
        this.image = image;
        this.label = label;
    }

    public int getImage() {
        return image;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity)) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id &&
                image == that.image &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, label);
    }

    public Object getId() {
        return id;
    }
}
