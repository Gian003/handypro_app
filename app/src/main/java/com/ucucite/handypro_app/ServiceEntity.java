package com.ucucite.handypro_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "services")
public class ServiceEntity {
    @PrimaryKey(autoGenerate = true) public int id;
    public int image;

    public String service;
    public String worker;
    public double rating;
    public int reviews;
    public double price;
    public boolean hasDiscount;

    public ServiceEntity(int image, String service, String worker, double rating, int reviews, double price, boolean hasDiscount) {
        this.image = image;
        this.service = service;
        this.worker = worker;
        this.rating = rating;
        this.reviews = reviews;
        this.price = price;
        this.hasDiscount = hasDiscount;
    }
}
