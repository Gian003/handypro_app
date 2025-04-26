package com.ucucite.handypro_app;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "services")
public class ServiceEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

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

    public int getImage() {
        return image;
    }

    public String getService() {
        return service;
    }

    public String getWorker() {
        return worker;
    }

    public double getRating() {
        return rating;
    }

    public int getReviews() {
        return reviews;
    }

    public double getPrice() {
        return price;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // same reference
        if (o == null || getClass() != o.getClass()) return false; // null or different class

        ServiceEntity that = (ServiceEntity) o;

        return id == that.id &&
                image == that.image &&
                Double.compare(that.rating, rating) == 0 &&
                reviews == that.reviews &&
                Double.compare(that.price, price) == 0 &&
                hasDiscount == that.hasDiscount &&
                Objects.equals(service, that.service) &&
                Objects.equals(worker, that.worker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, service, worker, rating, reviews, price, hasDiscount);
    }
}
