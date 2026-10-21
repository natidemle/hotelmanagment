package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    // ይለፍ ቃል ለማመንጨት (hash)
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    
    // ይለፍ ቃል ለማረጋገጥ
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}