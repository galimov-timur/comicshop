package kz.comicshop.entity;

import kz.comicshop.util.CurrencyUtil;

import java.io.Serializable;

/**
 * OrderItem entity represents a Product which is already in a shopping cart, contains
 * products information, and how many of this product were put in a shopping cart
 */
public class OrderItem implements Serializable {

    private Product product;
    private int quantity;

    public OrderItem() {}

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public String getTotalInCurrency() {
        double total = product.getPrice() * quantity;
        String totalInCurrency = CurrencyUtil.convert(total);
        return totalInCurrency;
    }
}
