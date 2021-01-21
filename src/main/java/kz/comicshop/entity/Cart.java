package kz.comicshop.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<OrderItem> cartProducts;

    public Cart() {
        cartProducts = new ArrayList<OrderItem>();
    }

    public ArrayList<OrderItem> getCartProducts() {
        return cartProducts;
    }

    public int getSize() {
        return cartProducts.size();
    }

    public void addProduct(OrderItem item) {
        long productId = item.getProduct().getId();
        int quantity = item.getQuantity();
        for(int i = 0; i < cartProducts.size(); i++) {
            OrderItem orderItem = cartProducts.get(i);
            if(orderItem.getProduct().getId() == productId) {
                orderItem.setQuantity(quantity);
                return;
            }
        }
        cartProducts.add(item);
    }

    public void removeProduct(long productId) {
        for(int i = 0; i < cartProducts.size(); i++) {
            OrderItem orderItem = cartProducts.get(i);
            if(orderItem.getProduct().getId() == productId) {
                cartProducts.remove(i);
                return;
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for(OrderItem item: cartProducts) {
            total += item.getTotal();
        }
        return total;
    }

    public void empty() {
        cartProducts.clear();
    }
}
