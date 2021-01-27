package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;
import kz.comicshop.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class AddProductService implements Service {

    static final Logger logger = Logger.getLogger(AddProductService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ConfigurationManager.getProperty("path.page.add_product");
        String action = request.getParameter(ACTION);

        if(action != null) {
            if(action.equals(ADD)) {

                String productCategoryId = request.getParameter("productCategory");
                String name = request.getParameter("productName");
                String description = request.getParameter("productDescription");
                String productQuantity = request.getParameter("productQuantity");
                String productPrice = request.getParameter("productPrice");

                double price = 0.0;
                int quantity = 0;
                long categoryId = 0;

                try {
                    price = Double.parseDouble(productPrice);
                    quantity = Integer.parseInt(productQuantity);
                    categoryId = Long.parseLong(productCategoryId);
                } catch(NumberFormatException e) {
                    logger.error(e.getMessage());
                }

                Part filePart = request.getPart("productImage");
                InputStream fileInputStream = filePart.getInputStream();

                String imageDest = request.getServletContext().getInitParameter("imageSource");
                File fileToSave = new File(imageDest + filePart.getSubmittedFileName());
                Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

                String productImageUrl = "/catalog/" + filePart.getSubmittedFileName();

                Product product = new Product(categoryId, name, description, price, productImageUrl, quantity);
                int rowsInserted = ProductDAO.insertProduct(product);

                String message;
                if(rowsInserted != 0) {
                    message = "<div class='message --success'><p>Ваш товар успешно добавлен</p></div>";
                } else {
                    message = "<div class='message --warning'><p>Не удалось добавить товар</p></div>";
                }
                request.setAttribute(MESSAGE, message);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }
}
