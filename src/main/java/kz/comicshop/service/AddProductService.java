package kz.comicshop.service;

import static kz.comicshop.service.constants.ProductConstants.*;
import static kz.comicshop.service.constants.CommonConstants.*;

import kz.comicshop.data.ProductDAO;
import kz.comicshop.entity.Product;
import kz.comicshop.util.*;
import kz.comicshop.validation.ProductValidator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;

/**
 * AddProductService - provides service to add new product in database. All input information
 * is validated by ProductValidator class in util package.
 */
public class AddProductService implements Service {
    static final Logger LOGGER = Logger.getLogger(AddProductService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        String destPage = ADD_PRODUCT_PAGE;
        String action = request.getParameter(ACTION);
        String message;

        if(action != null) {

            if(action.equals(ADD)) {
                ProductValidator productValidator = new ProductValidator();
                String productImageUrl = EMPTY_STRING;
                Part filePart = request.getPart(PRODUCT_IMAGE);
                Product product;

                if(filePart.getSize() != 0) {
                    productImageUrl = saveImage(filePart, request);
                }
                productValidator.setProductImage(productImageUrl);
                product = validateProductParameters(request, productValidator);

                if(product != null) {
                    product.setImageUrl(productImageUrl);
                    int rowsInserted = ProductDAO.insertProduct(product);
                    MessageManager messageManager = MessageManager.getInstance();

                    if(rowsInserted > 0) {
                        message = messageManager.getSuccessMessage("message.product.add.success");
                    } else {
                        message = messageManager.getWarningMessage("message.product.add.failure");
                    }
                    request.setAttribute(MESSAGE, message);
                } else {
                    request.setAttribute(FORM, productValidator);
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
    }

    /**
     * Saves product image to the external image folder, which is mapped to a web application
     * @param filePart - Part object from a request
     * @param request - HttpServletRequest
     * @return - path to the downloaded image in a web application
     */
    private String saveImage(Part filePart, HttpServletRequest request) {
        try {
            InputStream fileInputStream = filePart.getInputStream();
            String pathToImages = ConfigurationManager.getProperty("path.image.source");
            File destFile = new File(pathToImages + filePart.getSubmittedFileName());
            Files.copy(fileInputStream, destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e) {
            LOGGER.error(e.getMessage());
        }
        String pathToImagesInApp = ConfigurationManager.getProperty("path.image.web");
        String productImageUrl = pathToImagesInApp + filePart.getSubmittedFileName();
        return productImageUrl;
    }

    private Product validateProductParameters(HttpServletRequest request, ProductValidator productValidator) {
        String productCategoryId = request.getParameter(PRODUCT_CATEGORY);
        String name = request.getParameter(PRODUCT_NAME);
        String description = request.getParameter(PRODUCT_DESCRIPTION);
        String productQuantity = request.getParameter(PRODUCT_QUANTITY);
        String productPrice = request.getParameter(PRODUCT_PRICE);

        productValidator.setProductCategory(productCategoryId);
        productValidator.setProductName(name);
        productValidator.setProductDescription(description);
        productValidator.setProductQuantity(productQuantity);
        productValidator.setProductPrice(productPrice);

        if(productValidator.process()){
            long categoryId = 0;
            int quantity = 0;
            double price = 0;

            try {
                categoryId = Long.parseLong(productCategoryId);
                quantity = Integer.parseInt(productQuantity);
                price = Double.parseDouble(productPrice);
            } catch(NumberFormatException e) {
                LOGGER.error(e.getMessage());
            }

            Product product = new Product();
            product.setCategoryId(categoryId);
            product.setName(name);
            product.setDescription(description);
            product.setQuantity(quantity);
            product.setPrice(price);
            return product;
        }
        return null;
    }
}
