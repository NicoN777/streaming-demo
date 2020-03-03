package com.github.nicon777.kafkaexampleconsumer.model;

import java.util.List;

public class Product {
    private int id;
    private String brand;
    private String name;
    private String description;
    private float price;
    private List<Review> reviews;

    public Product() {
    }

    public Product(int id, String brand, String name, String description, float price, List<Review> reviews) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
