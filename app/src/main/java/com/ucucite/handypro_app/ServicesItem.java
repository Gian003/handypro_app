package com.ucucite.handypro_app;

public class ServicesItem {
    private int image;
    private String Service;
    private String worker;
    private double rating;
    private int reviews;
    private double Price;
    private boolean hasDiscount;

    public ServicesItem(int image, String Service, String worker, double rating, int reviews, double Price, boolean hasDiscount) {
        this.image = image;
        this.Service = Service;
        this.worker = worker;
        this.rating = rating;
        this.reviews = reviews;
        this.Price = Price;
        this.hasDiscount = hasDiscount;
    }

    public int getImage() {
        return image;
    }

    public String getService() {
        return Service;
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
        return Price;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

}
