package kz.comicshop.filter;

import kz.comicshop.entity.User;
import kz.comicshop.service.Service;
import kz.comicshop.util.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminAuthenticationFilter implements Filter {

    private static final String[] RESTRICTED_URLS = {"/COMICSHOP/ADMIN/PRODUCT/DELETE", "/COMICSHOP/ADMIN/CATEGORY",
            "/COMICSHOP/ADMIN/PRODUCT/ADD", "/COMICSHOP/ADMIN/ORDER", "/COMICSHOP/ADMIN/MENU"};

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
        String requestUrl = httpRequest.getRequestURI().toString();
        for(String url: RESTRICTED_URLS) {
            if(requestUrl.equalsIgnoreCase(url)) {
                isPermissionNeeded = true;
            }
        }

        if(!isPermissionNeeded) {
            filterChain.doFilter(request,response);
        } else {
            if (session != null) {
                String message = "";
                User user = (User) session.getAttribute(Service.USER);

                if(user != null) {
                    short userRole = user.getRole(); // 0 user, 1 - admin

                    if(userRole == 1) {
                        hasRights = true;
                        filterChain.doFilter(request,response);
                    } else {
                        message = "<div class='message --warning'><p>У вас недостаточно прав</p></div>";
                    }

                } else {
                    message = "<div class='message --warning'><p>Войдите на сайт используя свой логин и пароль</p></div>";
                }

                request.setAttribute(Service.MESSAGE, message);
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
