import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //Add new user to Users table in database
    public void addUser(User user)
    {
        String query = "INSERT INTO Users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query))
        {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
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
        String query = "SELECT user_id, username FROM Users";

        try (Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet rs = statement.executeQuery();)
        {
            while (rs.next())
            {
                int userID = rs.getInt("user_id");
                String username = rs.getString("username");

                users.add("ID: " + userID + ", Username: " + username);
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
        String query = "DELETE FROM Users WHERE user_id = ?";
        try (
            Connection con = DBConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query)) 
            {
            statement.setInt(1, userID);
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //User in database validation
    public boolean isUserInDatabase(String username) throws SQLException
    {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";

        try (Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query)) {
       
       statement.setString(1, username);
       try (ResultSet resultSet = statement.executeQuery()) {
           if (resultSet.next()) {
               return resultSet.getInt(1) > 0; // Return true if count > 0
           }
       }
   }
   return false;
    }
}
