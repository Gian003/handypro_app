package com.ucucite.handypro_app;
public class ServiceEntity {
    public String Service;
    public String worker;
    public double rating;
    public int reviews;
    public double price;
    public boolean hasDiscount;

    public ServiceEntity(String service, String worker, double rating, int reviews, double price, boolean hasDiscount) {
        this.Service = service;
        this.worker = worker;
        this.rating = rating;
        this.reviews = reviews;
        this.price = price;
        this.hasDiscount = hasDiscount;
    }
}
