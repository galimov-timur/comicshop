package kz.comicshop.util;

public class CurrencyUtil {
    private static final MessageManager MESSAGE_MANAGER = MessageManager.getInstance();

    /**
     * Converts price using selected locale, and adds currency symbol at the and of the price
     * @param price - double, Price
     * @return - String, converted price
     */
    public static String convert(double price) {
        String currencySymbol = MESSAGE_MANAGER.getMessage("product.currency");
        String conversionRate = MESSAGE_MANAGER.getMessage("currency.exchange.rate");
        double conversion = Double.parseDouble(conversionRate);
        double convertedPrice = Math.round((price/conversion) * 100.0)/100.0;
        return convertedPrice+currencySymbol;
    }
}
