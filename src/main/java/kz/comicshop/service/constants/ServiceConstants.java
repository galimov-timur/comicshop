package kz.comicshop.service.constants;

import kz.comicshop.util.ConfigurationManager;

public final class ServiceConstants {
    private ServiceConstants() {}
    public static final String ERROR_SERVICE = ConfigurationManager.getProperty("path.service.error");
    public static final String HOME_SERVICE = ConfigurationManager.getProperty("path.service.home");
    public static final String LANG_SERVICE = ConfigurationManager.getProperty("path.service.language");
    public static final String LOGIN_SERVICE = ConfigurationManager.getProperty("path.service.login");
    public static final String LOGOUT_SERVICE = ConfigurationManager.getProperty("path.service.logout");
    public static final String SIGNUP_SERVICE = ConfigurationManager.getProperty("path.service.registration");
    public static final String SHOW_PRODUCT_SERVICE = ConfigurationManager.getProperty("path.service.product.show");
    public static final String SHOW_ORDER_SERVICE = ConfigurationManager.getProperty("path.service.order.show");
    public static final String SHOW_CATEGORY_SERVICE = ConfigurationManager.getProperty("path.service.category.show");
    public static final String CART_SERVICE = ConfigurationManager.getProperty("path.service.cart");
    public static final String CHECKOUT_SERVICE = ConfigurationManager.getProperty("path.service.checkout");
    public static final String ADMIN_SERVICE = ConfigurationManager.getProperty("path.service.admin");
    public static final String ADMIN_ORDER_SERVICE = ConfigurationManager.getProperty("path.service.admin.order");
    public static final String ADD_PRODUCT_SERVICE = ConfigurationManager.getProperty("path.service.product.add");
    public static final String DELETE_PRODUCT_SERVICE = ConfigurationManager.getProperty("path.service.product.delete");
    public static final String ADMIN_CATEGORY_SERVICE = ConfigurationManager.getProperty("path.service.admin.category");
}
