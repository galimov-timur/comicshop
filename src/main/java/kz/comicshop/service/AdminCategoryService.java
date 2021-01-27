package kz.comicshop.service;

import kz.comicshop.data.CategoryDAO;
import kz.comicshop.entity.Category;
import kz.comicshop.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminCategoryService implements Service {

    static final Logger logger = Logger.getLogger(AdminCategoryService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ConfigurationManager.getProperty("path.page.category");
        String action = request.getParameter(ACTION);


        if(action != null) {
            String message = "";

            if(action.equals(ADD)) {
                String categoryName = request.getParameter(NAME);
                Category newCategory = new Category(categoryName);

                int result = CategoryDAO.insertCategory(newCategory);

                if(result > 0) {
                    message = "<div class='message --success'><p>Категория успешно добавлена</p></div>";
                    ServletContext application = request.getServletContext();
                    application.removeAttribute(CATEGORIES);
                    ArrayList<Category> categories = CategoryDAO.getCategories();
                    application.setAttribute(CATEGORIES, categories);
                } else {
                    message = "<div class='message --warning'><p>Не удалось добавить категорию</p></div>";
                }

            } else if(action.equals(REMOVE)) {
                String categoryId = request.getParameter(ID);

                long categoryIdAsLong = 0;

                try {
                    categoryIdAsLong = Long.parseLong(categoryId);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage());
                }

                int result = CategoryDAO.delete(categoryIdAsLong);

                if(result > 0) {
                    message = "<div class='message --success'><p>Категория успешно удалена</p></div>";
                    ServletContext application = request.getServletContext();
                    ArrayList<Category> categories = (ArrayList<Category>) application.getAttribute(CATEGORIES);

                    for(int i = 0; i < categories.size(); i++) {
                        if(categories.get(i).getId() == categoryIdAsLong) {
                            categories.remove(i);
                        }
                    }

                    application.setAttribute(CATEGORIES, categories);
                } else {
                    message = "<div class='message --warning'><p>Не удалось удалить категорию</p></div>";
                }
            }
            request.setAttribute(MESSAGE, message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
