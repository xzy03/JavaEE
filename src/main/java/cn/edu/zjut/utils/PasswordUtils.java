package cn.edu.zjut.utils;
import cn.hutool.crypto.digest.BCrypt;
public class PasswordUtils {
    public static boolean verify(String password, String bytPassword){
        return BCrypt.checkpw(password,bytPassword);
    }
    public static String encrypt(String password){
        return BCrypt.hashpw(password);
    }
}
