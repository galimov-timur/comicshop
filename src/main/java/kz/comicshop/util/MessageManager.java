package kz.comicshop.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static MessageManager messageManager;
    private static ResourceBundle messageResource;
    private static final String  messageResourceName = "messages";

    private MessageManager() {
        messageResource = ResourceBundle.getBundle(messageResourceName, Locale.getDefault());
    }

    public static synchronized MessageManager getInstance() {
        if(messageManager == null) {
            messageManager = new MessageManager();
        }
        return messageManager;
    }

    public static void changeResource(Locale locale) {
        messageResource = ResourceBundle.getBundle(messageResourceName, locale);
    }

    public static String getMessage(String key) {
        return messageResource.getString(key);
    }

    public static String getWarningMessage(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='message --warning'><p>");
        sb.append(messageResource.getString(key));
        sb.append("</p></div>");
        return sb.toString();
    }

    public static String getSuccessMessage(String key) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='message --success'><p>");
        sb.append(messageResource.getString(key));
        sb.append("</p></div>");
        return sb.toString();
    }
}
