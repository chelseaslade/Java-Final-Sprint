import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

//UserService is an intermediary between userDAO and user interface
public class UserService {
    //Init
    private UserDAO userDao = new UserDAO();

    public void addUser(User user)
    {
        userDao.addUser(user);
    }

    public List<String> viewAllUsers()
    {
       return userDao.viewAllUsers();
    }

    public void deleteUser(int userID)
    {
        userDao.deleteUser(userID);
    }

    public boolean isUserInDatabase(String username)
    {
        try 
        {
        return userDao.isUserInDatabase(username);
    }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public int getSellerIdByUsername(String username) {
    String query = "SELECT user_id FROM Users WHERE username = ? AND role = 'seller'";
    try (
        Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query)
    ) {
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("user_id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Return -1 if not found
}

    public String authenticate(String username, String plaintextPW) {
    String sql = "SELECT password, role FROM users WHERE username = ?";
    try (Connection con = DBConnection.getCon();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Set the username parameter
        ps.setString(1, username);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Get the stored hashed password and role
                String hashedPassword = rs.getString("password");
                String role = rs.getString("role");

                // Verify the plaintext password against the hashed password
                if (BCrypt.checkpw(plaintextPW, hashedPassword)) {
                    // Return the role if the password matches
                    return role;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    // Return null if authentication fails
    return null;
    }
}
