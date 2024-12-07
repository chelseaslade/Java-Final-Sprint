import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    String query = "SELECT id FROM Users WHERE username = ? AND role = 'seller'";
    try (
        Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query)
    ) {
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Return -1 if not found
}
}
