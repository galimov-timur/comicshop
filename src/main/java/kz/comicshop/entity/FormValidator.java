package kz.comicshop.entity;

import kz.comicshop.data.UserDAO;

import java.io.Serializable;
import java.util.HashMap;

public class FormValidator implements Serializable {

    HashMap<String, Integer> errorCodes = new HashMap<>();
    HashMap<Integer, String> codeMsgMap = new HashMap<>();

    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    private String password = "";
    private String passwordRepeat = "";

    // RegEx
    public static final String emailRegEx = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String stringRegEx = "[А-Яа-яA-Za-z]+";
    public static final String phoneRegEx = "^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$";

    // Error codes
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
        codeMsgMap.put(ERR_FIRST_NAME_BLANK, "Пожалуйста введите имя");
        codeMsgMap.put(ERR_FIRST_NAME_INVALID, "Неверное значение в поле имя");
        codeMsgMap.put(ERR_LAST_NAME_BLANK, "Пожалуйста введите вашу фамилию");
        codeMsgMap.put(ERR_LAST_NAME_INVALID, "Неверное значение в поле фамилия");
        codeMsgMap.put(ERR_EMAIL_BLANK, "Пожалуйста введите email");
        codeMsgMap.put(ERR_EMAIL_INVALID, "Неверное значение в поле email");
        codeMsgMap.put(ERR_EMAIL_EXISTS, "Пользователь с таким email адресом уже зарегистрирован");
        codeMsgMap.put(ERR_PHONE_BLANK, "Введите номер вашего телефона");
        codeMsgMap.put(ERR_PHONE_INVALID, "Правильный формат телефона +7(707)123-45-46 без пробелов");
        codeMsgMap.put(ERR_PASSWORD_BLANK, "Введите пароль");
        codeMsgMap.put(ERR_PASSWORD_INVALID, "Неверное значение в поле телефон");
        codeMsgMap.put(ERR_PASSWORD_LENGTH, "Длина пароля не должна быть меньше 4 знаков");
        codeMsgMap.put(ERR_PASSWORD_REPEAT, "Пароли не совпадают, введенные пароли должны совпадать");
    }

    public String getErrorMessage(String formField){
        Integer code =(Integer) (errorCodes.get(formField));
        if(code == null) {
            return "";
        } else {
            return codeMsgMap.get(code);
        }
    }

    public boolean isValid() {
        errorCodes.clear();

        if(firstName.length() == 0) {
            errorCodes.put("firstName", ERR_FIRST_NAME_BLANK);
        } else if(!firstName.matches(stringRegEx)) {
            errorCodes.put("firstName", ERR_FIRST_NAME_INVALID);
        }

        if(lastName.length() == 0) {
            errorCodes.put("lastName", ERR_LAST_NAME_BLANK);
        } else if(!lastName.matches(stringRegEx)) {
            errorCodes.put("lastName", ERR_LAST_NAME_INVALID);
        }

        if(email.length() == 0) {
            errorCodes.put("email", ERR_EMAIL_BLANK);
        } else if(!email.matches(emailRegEx)) {
            errorCodes.put("email", ERR_EMAIL_INVALID);
        }

        User user = UserDAO.getUserByEmail(email);
        if(user != null) {
            errorCodes.put("email", ERR_EMAIL_EXISTS);
        }

        if(phone.length() == 0) {
            errorCodes.put("phone", ERR_PHONE_BLANK);
        } else if(!phone.matches(phoneRegEx)) {
            errorCodes.put("phone", ERR_PHONE_INVALID);
        }

        if(password.length() == 0) {
            errorCodes.put("password", ERR_PASSWORD_BLANK);
        } else if(password.length() < 4) {
            errorCodes.put("password", ERR_PASSWORD_LENGTH);
        } else if(!passwordRepeat.equals(password)) {
            errorCodes.put("passwordRepeat", ERR_PASSWORD_REPEAT);
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
