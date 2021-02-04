package kz.comicshop.service.factory;

import static kz.comicshop.service.constants.ServiceConstants.*;
import kz.comicshop.service.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private ServiceFactory() {}

    static {
        SERVICE_MAP.put(ERROR_SERVICE, new ErrorService());
        SERVICE_MAP.put(HOME_SERVICE, new HomeService());
        SERVICE_MAP.put(LANG_SERVICE, new LanguageService());
        SERVICE_MAP.put(LOGIN_SERVICE, new LoginService());
        SERVICE_MAP.put(LOGOUT_SERVICE, new LogoutService());
        SERVICE_MAP.put(SIGNUP_SERVICE, new RegistrationService());
        SERVICE_MAP.put(SHOW_PRODUCT_SERVICE, new ShowProductService());
        SERVICE_MAP.put(SHOW_ORDER_SERVICE, new ShowOrderService());
        SERVICE_MAP.put(SHOW_CATEGORY_SERVICE, new ShowCategoryService());
        SERVICE_MAP.put(CART_SERVICE, new CartService());
        SERVICE_MAP.put(CHECKOUT_SERVICE, new CheckoutService());
        SERVICE_MAP.put(ADMIN_SERVICE, new AdminService());
        SERVICE_MAP.put(ADMIN_ORDER_SERVICE, new AdminOrderService());
        SERVICE_MAP.put(ADD_PRODUCT_SERVICE, new AddProductService());
        SERVICE_MAP.put(DELETE_PRODUCT_SERVICE, new DeleteProductService());
        SERVICE_MAP.put(ADMIN_CATEGORY_SERVICE, new AdminCategoryService());
    }

    public Service getService(String request) {
        Service service = SERVICE_MAP.get(request.toUpperCase());
        if(service == null) {
            service = SERVICE_MAP.get(ERROR_SERVICE);
        }
        return service;
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }
}