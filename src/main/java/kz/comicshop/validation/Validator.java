package kz.comicshop.validation;

import static kz.comicshop.service.constants.CommonConstants.*;
import kz.comicshop.util.MessageManager;

import java.util.HashMap;

public abstract class Validator {
    protected MessageManager MESSAGE_MANAGER = MessageManager.getInstance();
    protected HashMap<String, Integer> errorCodes = new HashMap<>();
    protected HashMap<Integer, String> codeMsgMap = new HashMap<>();

    public abstract boolean isValid();
    public abstract boolean process();

    public String getErrorMessage(String formField){
        Integer code =(Integer) (errorCodes.get(formField));
        if(code == null) {
            return EMPTY_STRING;
        } else {
            return codeMsgMap.get(code);
        }
    }
}
