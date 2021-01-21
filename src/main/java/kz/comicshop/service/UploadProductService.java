package kz.comicshop.service;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

public class UploadProductService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String url = "/add_product.jsp";

        String productCategoryId = request.getParameter("productCategory");
        long categoryId = Long.parseLong(productCategoryId);

        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");

        String productQuantity = request.getParameter("productQuantity");
        int quantity = Integer.parseInt(productQuantity);

        String productPrice = request.getParameter("productPrice");
        double price = Double.parseDouble(productPrice);

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
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
