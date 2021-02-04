package kz.comicshop.entity;

import kz.comicshop.util.CurrencyUtil;

import java.io.Serializable;

/**
 * Product entity implementation
 */
public class Product implements Serializable {

    private long id;
    private long categoryId;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private int quantity;

    public Product() {}

    public Product(long category_id, String name, String description, double price, String imageUrl, int quantity) {
        this.categoryId = category_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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

    public String getPriceInCurrency() {
        String priceInCurrency = CurrencyUtil.convert(price);
        return priceInCurrency;
    }
}
