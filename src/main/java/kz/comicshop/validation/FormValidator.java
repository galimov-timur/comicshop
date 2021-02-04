package kz.comicshop.validation;

import static kz.comicshop.service.constants.UserConstants.*;
import kz.comicshop.data.UserDAO;
import kz.comicshop.entity.User;
import kz.comicshop.util.ConfigurationManager;

import java.io.Serializable;

public class FormValidator extends Validator implements Serializable {
    public static final String EMAIL_REG_EX = ConfigurationManager.getProperty("regex.email");
    public static final String STRING_REG_EX = ConfigurationManager.getProperty("regex.string");
    public static final String PHONE_REG_EX = ConfigurationManager.getProperty("regex.phone");

    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    private String password = "";
    private String passwordRepeat = "";

    public static final Integer ERR_FIRST_NAME_BLANK = 1;
    public static final Integer ERR_FIRST_NAME_INVALID = 2;
    public static final Integer ERR_LAST_NAME_BLANK = 3;
    public static final Integer ERR_LAST_NAME_INVALID = 4;
    public static final Integer ERR_EMAIL_BLANK = 5;
    public static final Integer ERR_EMAIL_INVALID = 6;
    public static final Integer ERR_EMAIL_EXISTS = 7;
    public static final Integer ERR_PHONE_BLANK = 8;
    public static final Integer ERR_PHONE_INVALID = 9;
    public static final Integer ERR_PASSWORD_BLANK = 10;
    public static final Integer ERR_PASSWORD_INVALID = 11;
    public static final Integer ERR_PASSWORD_LENGTH = 12;
    public static final Integer ERR_PASSWORD_REPEAT = 13;

    {
        codeMsgMap.put(ERR_FIRST_NAME_BLANK, MESSAGE_MANAGER.getMessage("error.form.name.blank"));
        codeMsgMap.put(ERR_FIRST_NAME_INVALID, MESSAGE_MANAGER.getMessage("error.form.name.invalid"));
        codeMsgMap.put(ERR_LAST_NAME_BLANK, MESSAGE_MANAGER.getMessage("error.form.lastname.blank"));
        codeMsgMap.put(ERR_LAST_NAME_INVALID, MESSAGE_MANAGER.getMessage("error.form.lastname.invalid"));
        codeMsgMap.put(ERR_EMAIL_BLANK, MESSAGE_MANAGER.getMessage("error.form.email.blank"));
        codeMsgMap.put(ERR_EMAIL_INVALID, MESSAGE_MANAGER.getMessage("error.form.email.invalid"));
        codeMsgMap.put(ERR_EMAIL_EXISTS, MESSAGE_MANAGER.getMessage("error.form.email.exists"));
        codeMsgMap.put(ERR_PHONE_BLANK, MESSAGE_MANAGER.getMessage("error.form.phone.blank"));
        codeMsgMap.put(ERR_PHONE_INVALID, MESSAGE_MANAGER.getMessage("error.form.phone.invalid"));
        codeMsgMap.put(ERR_PASSWORD_BLANK, MESSAGE_MANAGER.getMessage("error.form.password.blank"));
        codeMsgMap.put(ERR_PASSWORD_INVALID, MESSAGE_MANAGER.getMessage("error.form.password.invalid"));
        codeMsgMap.put(ERR_PASSWORD_LENGTH, MESSAGE_MANAGER.getMessage("error.form.password.length"));
        codeMsgMap.put(ERR_PASSWORD_REPEAT, MESSAGE_MANAGER.getMessage("error.form.password.repeat"));
    }

    public boolean isValid() {
        errorCodes.clear();

        if(firstName.length() == 0) {
            errorCodes.put(FIRST_NAME, ERR_FIRST_NAME_BLANK);
        } else if(!firstName.matches(STRING_REG_EX)) {
            errorCodes.put(FIRST_NAME, ERR_FIRST_NAME_INVALID);
        }

        if(lastName.length() == 0) {
            errorCodes.put(LAST_NAME, ERR_LAST_NAME_BLANK);
        } else if(!lastName.matches(STRING_REG_EX)) {
            errorCodes.put(LAST_NAME, ERR_LAST_NAME_INVALID);
        }

        if(email.length() == 0) {
            errorCodes.put(EMAIL, ERR_EMAIL_BLANK);
        } else if(!email.matches(EMAIL_REG_EX)) {
            errorCodes.put(EMAIL, ERR_EMAIL_INVALID);
        }

        User user = UserDAO.getUserByEmail(email);
        if(user != null) {
            errorCodes.put(EMAIL, ERR_EMAIL_EXISTS);
        }

        if(phone.length() == 0) {
            errorCodes.put(PHONE, ERR_PHONE_BLANK);
        } else if(!phone.matches(PHONE_REG_EX)) {
            errorCodes.put(PHONE, ERR_PHONE_INVALID);
        }

        int minPasswordLength = 4;
        if(password.length() == 0) {
            errorCodes.put(PASSWORD, ERR_PASSWORD_BLANK);
        } else if(password.length() < minPasswordLength) {
            errorCodes.put(PASSWORD, ERR_PASSWORD_LENGTH);
        } else if(!passwordRepeat.equals(password)) {
            errorCodes.put(PASSWORD_REPEAT, ERR_PASSWORD_REPEAT);
        }

        return errorCodes.size() == 0;
    }

    public boolean process() {
        if(!isValid()) {
            return false;
        }
        firstName = "";
        lastName = "";
        email = "";
        phone = "";
        password = "";
        passwordRepeat = "";
        errorCodes.clear();
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
