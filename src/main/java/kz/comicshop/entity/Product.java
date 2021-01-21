package kz.comicshop.entity;

import java.io.Serializable;

public class Product implements Serializable {

    private long id;
    private long category_id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private int quantity;

    public Product() {
        this.category_id = 0;
        this.name = "";
        this.description = "";
        this.price = 0.00;
        this.imageUrl = "";
        this.quantity = 0;
    }

    public Product(long category_id, String name, String description, double price, String imageUrl, int quantity) {
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
