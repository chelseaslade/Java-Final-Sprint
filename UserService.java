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
}
