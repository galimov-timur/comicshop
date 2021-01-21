package kz.comicshop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderDetails
 * orderStatus values: 0 - new, 1 - processing, 2 - delivered
 */
public class OrderDetails {

    private User user;
    private ArrayList<OrderItem> orderItems;
    private String orderDate;
    private double totalAmount;
    private short orderStatus;

    public OrderDetails() {
        this.user = null;
        this.orderItems = null;
        this.orderDate = null;
        this.totalAmount = 0.00;
        this.orderStatus = 0;
    }

    public OrderDetails(User user, ArrayList<OrderItem> orderItems, String orderDate, double totalAmount, short orderStatus) {
        this.user = user;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(short orderStatus) {
        this.orderStatus = orderStatus;
    }
}
