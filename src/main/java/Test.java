import kz.comicshop.util.PasswordUtil;

import java.security.NoSuchAlgorithmException;

public class Test {
    public static void main(String[] args) {
        String salt = PasswordUtil.saltPassword("abdsasef");
        String pas="";
        String pas2="";
        try {
            pas = PasswordUtil.hashPassword(salt + "abdsasef");
            pas2 = PasswordUtil.hashPassword(salt + "abdsasef");
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(pas);
        System.out.println(pas2);
        System.out.println(pas.equals(pas2));
    }
}
