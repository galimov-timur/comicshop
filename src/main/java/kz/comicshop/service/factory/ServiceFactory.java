package kz.comicshop.service.factory;

import kz.comicshop.service.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private ServiceFactory() {
    }

    static {
        SERVICE_MAP.put("/COMICSHOP/", new HomeService());
        SERVICE_MAP.put("/COMICSHOP/LOGIN", new LoginService());
        SERVICE_MAP.put("/COMICSHOP/LOGOUT", new LogoutService());
        SERVICE_MAP.put("/COMICSHOP/SIGNUP", new RegistrationService());
        SERVICE_MAP.put("/COMICSHOP/PRODUCT/SHOW", new ShowProductService());
        SERVICE_MAP.put("/COMICSHOP/ORDER/SHOW", new ShowOrderService());
        SERVICE_MAP.put("/COMICSHOP/CART", new CartService());
        SERVICE_MAP.put("/COMICSHOP/CHECKOUT", new CheckoutService());
        SERVICE_MAP.put("/COMICSHOP/ADMIN/MENU", new AdminService());
        SERVICE_MAP.put("/COMICSHOP/ADMIN/ORDER", new AdminOrderService());
        SERVICE_MAP.put("/COMICSHOP/ADMIN/ADD_PRODUCT", new AddProductService());
        SERVICE_MAP.put("/COMICSHOP/ADMIN/UPLOAD_PRODUCT", new UploadProductService());

    }

    public Service getService(String request) {
        Service service = SERVICE_MAP.get("/ERROR");

        for (Map.Entry<String, Service> pair : SERVICE_MAP.entrySet()) {
            if (request.equalsIgnoreCase(pair.getKey())) {
                service = SERVICE_MAP.get(pair.getKey());
            }
        }
        return service;
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }
}