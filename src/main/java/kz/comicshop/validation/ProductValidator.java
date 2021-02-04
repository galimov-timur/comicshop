package kz.comicshop.validation;

import static kz.comicshop.service.constants.ProductConstants.*;

import java.io.Serializable;

public class ProductValidator extends Validator implements Serializable {
    private static final int MAX_PRODUCT_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 2500;

    private String productCategory = "";
    private String productName = "";
    private String productDescription = "";
    private String productQuantity = "";
    private String productPrice = "";
    private String productImage = "";

    public static final Integer ERR_PRODUCT_CATEGORY_BLANK = 1;
    public static final Integer ERR_PRODUCT_NAME_BLANK = 2;
    public static final Integer ERR_PRODUCT_NAME_LENGTH = 3;
    public static final Integer ERR_PRODUCT_DESC_BLANK = 4;
    public static final Integer ERR_PRODUCT_DESC_LENGTH = 5;
    public static final Integer ERR_PRODUCT_QUANTITY_BLANK = 6;
    public static final Integer ERR_PRODUCT_QUANTITY_INVALID = 7;
    public static final Integer ERR_PRODUCT_PRICE_BLANK = 8;
    public static final Integer ERR_PRODUCT_PRICE_INVALID = 9;
    public static final Integer ERR_PRODUCT_IMAGE = 10;

    {
        codeMsgMap.put(ERR_PRODUCT_CATEGORY_BLANK, MESSAGE_MANAGER.getMessage("error.product.category.blank"));
        codeMsgMap.put(ERR_PRODUCT_NAME_BLANK, MESSAGE_MANAGER.getMessage("error.product.name.blank"));
        codeMsgMap.put(ERR_PRODUCT_NAME_LENGTH, MESSAGE_MANAGER.getMessage("error.product.name.length"));
        codeMsgMap.put(ERR_PRODUCT_DESC_BLANK, MESSAGE_MANAGER.getMessage("error.product.desc.blank"));
        codeMsgMap.put(ERR_PRODUCT_DESC_LENGTH, MESSAGE_MANAGER.getMessage("error.product.desc.length"));
        codeMsgMap.put(ERR_PRODUCT_QUANTITY_BLANK, MESSAGE_MANAGER.getMessage("error.product.quantity.blank"));
        codeMsgMap.put(ERR_PRODUCT_QUANTITY_INVALID, MESSAGE_MANAGER.getMessage("error.product.quantity.invalid"));
        codeMsgMap.put(ERR_PRODUCT_PRICE_BLANK, MESSAGE_MANAGER.getMessage("error.product.price.blank"));
        codeMsgMap.put(ERR_PRODUCT_PRICE_INVALID, MESSAGE_MANAGER.getMessage("error.product.price.invalid"));
        codeMsgMap.put(ERR_PRODUCT_IMAGE, MESSAGE_MANAGER.getMessage("error.product.image"));
    }

    public boolean isValid() {
        errorCodes.clear();

        try {
            Long.parseLong(productCategory);
        } catch(NumberFormatException e) {
            errorCodes.put(PRODUCT_CATEGORY, ERR_PRODUCT_CATEGORY_BLANK);
        }

        if(productName.length() == 0) {
            errorCodes.put(PRODUCT_NAME, ERR_PRODUCT_NAME_BLANK);
        } else if(productName.length() > MAX_PRODUCT_LENGTH) {
            errorCodes.put(PRODUCT_NAME, ERR_PRODUCT_NAME_LENGTH);
        }

        if(productDescription.length() == 0) {
            errorCodes.put(PRODUCT_DESCRIPTION, ERR_PRODUCT_DESC_BLANK);
        } else if(productDescription.length() > MAX_DESCRIPTION_LENGTH) {
            errorCodes.put(PRODUCT_DESCRIPTION, ERR_PRODUCT_DESC_LENGTH);
        }

        if(productQuantity.length() == 0) {
            errorCodes.put(PRODUCT_QUANTITY, ERR_PRODUCT_QUANTITY_BLANK);
        } else {
            int quantity = -1;
            try {
                quantity = Integer.parseInt(productQuantity);
                if(quantity <= 0) {
                    errorCodes.put(PRODUCT_QUANTITY, ERR_PRODUCT_QUANTITY_INVALID);
                }
            } catch (NumberFormatException e) {
                errorCodes.put(PRODUCT_QUANTITY, ERR_PRODUCT_QUANTITY_INVALID);
            }
        }

        if(productPrice.length() == 0) {
            errorCodes.put(PRODUCT_PRICE, ERR_PRODUCT_PRICE_BLANK);
        } else {
            double price = 0;
            try {
                price = Double.parseDouble(productPrice);
                if(price <= 0) {
                    errorCodes.put(PRODUCT_PRICE, ERR_PRODUCT_PRICE_INVALID);
                }
            } catch (NumberFormatException e) {
                errorCodes.put(PRODUCT_PRICE, ERR_PRODUCT_PRICE_INVALID);
            }
        }

        if(productImage == null || productImage.equals("")) {
            errorCodes.put(PRODUCT_IMAGE, ERR_PRODUCT_IMAGE);
        }

        return errorCodes.size() == 0;
    }

    public boolean process() {
        if(!isValid()) {
            return false;
        }
        productName = "";
        productDescription = "";
        productQuantity = "";
        productPrice = "";
        productImage = "";
        errorCodes.clear();
        return true;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
