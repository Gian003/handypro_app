package com.ucucite.handypro_app;

public class Carouselitem {
    int image;
    String promo;
    String Service;

    public Carouselitem(int image, String promo, String service) {
        this.image = image;
        this.promo = promo;
        Service = service;
    }

    public int getImage() {
        return image;
    }

    public String getPromo() {
        return promo;
    }

    public String getService() {
        return Service;
    }
}
