import org.mindrot.jbcrypt.BCrypt;

public class PasswordHash {
    //Hash plain text password
    public static String hashPassword(String plaintextPW) 
    {
        return BCrypt.hashpw(plaintextPW, BCrypt.gensalt());
    }

    //Verify hashed password
    public static boolean verifyPassword(String plaintextPW, String hashedPW)
    {
        return BCrypt.checkpw(plaintextPW, hashedPW);
    }
}
