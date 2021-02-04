package kz.comicshop.entity;

import kz.comicshop.util.CurrencyUtil;
import kz.comicshop.util.MessageManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 * Order details entity represents order information
 */
public class OrderDetails {

    private long orderId;
    private User user;
    private ArrayList<OrderItem> orderItems;
    private String orderDate;
    private double totalAmount;
    private short orderStatus;

    public OrderDetails() {}

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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String displayStatus() {
        MessageManager messageManager = MessageManager.getInstance();
        switch (orderStatus) {
            case 0:
                return messageManager.getMessage("order.status.new");
            case 1:
                return messageManager.getMessage("order.status.processing");
            case 2:
                return messageManager.getMessage("order.status.delivered");
            default:
                return "";
        }
    }

    public String getTime() throws ParseException {
        Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDate);
        String time = new SimpleDateFormat("HH:mm").format(dateTime);
        return time;
    }

    public String getDate() throws ParseException {
        Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderDate);
        String  date = new SimpleDateFormat("dd-MM-yyyy").format(dateTime);
        return date;
    }

    public String getTotal() {
        String totalInCurrency = CurrencyUtil.convert(totalAmount);
        return totalInCurrency;
    }
}
