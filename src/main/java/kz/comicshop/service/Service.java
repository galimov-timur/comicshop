package kz.comicshop.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {

    public static final String ORDER_DETAILS = "orderDetails";
    public static final String CART = "cart";
    public static final String USER = "user";
    public static final String MESSAGE = "message";
    public static final String PRODUCTS = "products";
    public static final String ORDERS = "orders";
    public static final String NEXT_PAGE = "nextPage";
    public static final String ACTION = "action";
    public static final String NAME = "name";
    public static final String ADD = "add";
    public static final String REMOVE = "remove";
    public static final String ID = "id";
    public static final String ORDER_ID = "orderId";

    // PAGES

    public static final String ADD_PRODUCT_PAGE = "/add_product.jsp";
    public static final String INDEX_PAGE = "/index.jsp";
    public static final String ORDER_PAGE = "/order.jsp";
    public static final String MANAGE_ORDER_PAGE = "/manage_order.jsp";
    public static final String ADMIN_PAGE = "/admin.jsp";
    public static final String CART_PAGE = "/cart.jsp";
    public static final String ADDRESS_PAGE = "/address.jsp";
    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String INVOICE_PAGE = "/invoice.jsp";
    public static final String THANKS_PAGE = "/thanks.jsp";

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  ParseException, SQLException;
}

