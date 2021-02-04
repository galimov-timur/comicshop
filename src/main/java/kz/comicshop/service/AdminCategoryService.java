package kz.comicshop.service;

import static kz.comicshop.service.constants.CommonConstants.*;

import kz.comicshop.data.CategoryDAO;
import kz.comicshop.entity.Category;
import kz.comicshop.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * AdminCategoryService - provides services for adding and removing categories in web application
 */
public class AdminCategoryService implements Service {
    private static final Logger LOGGER = Logger.getLogger(AdminCategoryService.class);
    private static final MessageManager MESSAGE_MANAGER = MessageManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = MANAGE_CATEGORIES_PAGE;
        String action = request.getParameter(ACTION);
        String message = EMPTY_STRING;

        if(action != null) {
            if(action.equals(ADD)) {
                message = addCategory(request);
            } else if(action.equals(REMOVE)) {
                String categoryId = request.getParameter(ID);
                long categoryIdAsLong = 0;
                try {
                    categoryIdAsLong = Long.parseLong(categoryId);
                } catch (NumberFormatException e) {
                    LOGGER.error(e.getMessage());
                }
                message = removeCategory(request, categoryIdAsLong);
            }
            request.setAttribute(MESSAGE, message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

    /**
     * Creates new category and saves it in database, then updates categories attribute in application context
     * @param request - HttpServletRequest
     * @return - String, result message
     */
    private String addCategory(HttpServletRequest request) {
        String message;
        String categoryName = request.getParameter(NAME);
        Category newCategory = new Category(categoryName);
        int result = CategoryDAO.insertCategory(newCategory);
        if(result > 0) {
            message = MESSAGE_MANAGER.getSuccessMessage("message.category.add.success");
            ServletContext application = request.getServletContext();
            application.removeAttribute(CATEGORIES);
            ArrayList<Category> categories = CategoryDAO.getCategories();
            application.setAttribute(CATEGORIES, categories);
        } else {
            message = MESSAGE_MANAGER.getWarningMessage("message.category.add.failure");
        }
        return message;
    }

    /**
     * Removes category from database by category id, then updates categories attribute in application context
     * @param request - HttpServletRequest
     * @param categoryId - long
     * @return - String, result message
     */
    private String removeCategory(HttpServletRequest request, long categoryId) {
        String message;
        int result = CategoryDAO.delete(categoryId);
        if(result > 0) {
            message = MESSAGE_MANAGER.getSuccessMessage("message.category.remove.success");
            ServletContext application = request.getServletContext();
            ArrayList<Category> categories = (ArrayList<Category>) application.getAttribute(CATEGORIES);
            int categoryIndex;
            for(categoryIndex = 0; categoryIndex < categories.size(); categoryIndex++) {
                if(categories.get(categoryIndex).getId() == categoryId) {
                    categories.remove(categoryIndex);
                }
            }
            application.setAttribute(CATEGORIES, categories);
        } else {
            message = MESSAGE_MANAGER.getWarningMessage("message.category.remove.failure");
        }
        return message;
    }
}
