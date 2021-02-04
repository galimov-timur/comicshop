package kz.comicshop.service.constants;

import kz.comicshop.util.ConfigurationManager;

public final class CommonConstants {
    private CommonConstants() {}

    // GENERAL
    public static final String MESSAGE = "message";
    public static final String ACTION = "action";
    public static final String EMPTY_STRING = "";
    public static final String NAME = "name";
    public static final String CATEGORIES = "categories";
    public static final String ID = "id";
    public static final String ORDERS = "orders";
    public static final String NEXT_PAGE = "nextPage";
    public static final String ORDER_ID = "orderId";
    public static final String CART = "cart";
    public static final String SIGN_IN = "signIn";
    public static final String USER = "user";
    public static final String FORM = "form";
    public static final String LANG = "lang";
    public static final String LANG_RU = "ru";

    // ACTIONS
    public static final String ADD = "add";
    public static final String REMOVE = "remove";
    public static final String EDIT = "edit";
    public static final String UPDATE = "update";
    public static final String SHOW = "show";

    // PAGES
    public static final String ADD_PRODUCT_PAGE = ConfigurationManager.getProperty("path.page.add_product");
    public static final String MANAGE_CATEGORIES_PAGE = ConfigurationManager.getProperty("path.page.category");
    public static final String ORDER_PAGE = ConfigurationManager.getProperty("path.page.order");
    public static final String MANAGE_ORDER_PAGE = ConfigurationManager.getProperty("path.page.manage_order");
    public static final String ADMIN_PAGE = ConfigurationManager.getProperty("path.page.admin");
    public static final String CART_PAGE = ConfigurationManager.getProperty("path.page.cart");
    public static final String LOGIN_PAGE = ConfigurationManager.getProperty("path.page.login");
    public static final String ADDRESS_PAGE = ConfigurationManager.getProperty("path.page.address");
    public static final String INVOICE_PAGE = ConfigurationManager.getProperty("path.page.invoice");
    public static final String THANK_YOU_PAGE = ConfigurationManager.getProperty("path.page.thanks");
    public static final String CATALOG_PAGE =  ConfigurationManager.getProperty("path.page.catalog");
    public static final String ERROR_PAGE = ConfigurationManager.getProperty("path.page.error");
    public static final String INDEX_PAGE = ConfigurationManager.getProperty("path.page.index");
    public static final String REGISTRATION_PAGE = ConfigurationManager.getProperty("path.page.registration");
    public static final String PRODUCT_PAGE = ConfigurationManager.getProperty("path.page.product");
}
