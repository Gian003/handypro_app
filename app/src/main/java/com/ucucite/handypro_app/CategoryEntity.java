package com.ucucite.handypro_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
}
