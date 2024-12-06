import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //Add new user to Users table in database
    public void addUser(String username, String password, String email, String role)
    {
        String query = "INSERT INTO Users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query))
        {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, role);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    //View all users in Users table
    public List<String> viewAllUsers()
    {
        List<String> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();)
        {
            while (rs.next())
            {
                users.add(rs.getString("username"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    //Delete User
    public void deleteUser(int userID)
    {

    }

    //Edit User
    public void editUser(int userID)
    {
        
    }

}
