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
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String SIGN_IN = "signIn";
    public static final String MESSAGE = "message";
    public static final String FORM = "form";
    public static final String PRODUCTS = "products";
    public static final String PRODUCT = "product";
    public static final String CATEGORIES = "categories";
    public static final String ORDERS = "orders";
    public static final String NEXT_PAGE = "nextPage";
    public static final String ACTION = "action";
    public static final String NAME = "name";
    public static final String SHOW = "show";
    public static final String ADD = "add";
    public static final String REMOVE = "remove";
    public static final String ID = "id";
    public static final String EDIT = "edit";
    public static final String ORDER_ID = "orderId";
    public static final String CATEGORY_ID = "categoryId";
    public static final String UPDATE = "update";
    public static final String ORDER_STATUS = "orderStatus";
    public static final String ERROR_CODE = "errorCode";

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,  ParseException, SQLException;
}

