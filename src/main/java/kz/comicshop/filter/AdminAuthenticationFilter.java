package kz.comicshop.filter;

import static kz.comicshop.service.constants.CommonConstants.*;
import static kz.comicshop.service.constants.ServiceConstants.*;

import kz.comicshop.entity.User;
import kz.comicshop.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * AdminAuthenticationFilter checks if a user has rights to access administrator services
 */
public class AdminAuthenticationFilter implements Filter {
    private static final String[] RESTRICTED_URLS = {DELETE_PRODUCT_SERVICE, ADMIN_CATEGORY_SERVICE,
            ADD_PRODUCT_SERVICE, ADMIN_ORDER_SERVICE, ADMIN_SERVICE};
    private static final short ADMIN_RIGHTS = 1;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String destPage = ConfigurationManager.getProperty("path.page.login");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        boolean hasRights = false;
        boolean isPermissionNeeded = false;
        String requestUrl = httpRequest.getRequestURI();
        for(String url: RESTRICTED_URLS) {
            if(requestUrl.equalsIgnoreCase(url)) {
                isPermissionNeeded = true;
            }
        }

        if(!isPermissionNeeded) {
            filterChain.doFilter(request,response);
        } else {
            if (session != null) {
                String message = EMPTY_STRING;
                User user = (User) session.getAttribute(USER);
                if(user != null) {
                    short userRole = user.getRole();
                    if(userRole == ADMIN_RIGHTS) {
                        hasRights = true;
                        filterChain.doFilter(request,response);
                    } else {
                        message = MessageManager.getWarningMessage("message.filter.no.rights");
                    }
                } else {
                    message = MessageManager.getWarningMessage("message.filter.login.needed");
                }
                request.setAttribute(MESSAGE, message);
            }
            if (session == null || !hasRights) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {}
}
