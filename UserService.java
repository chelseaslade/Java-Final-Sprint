import java.util.List;

//UserService is an intermediary between userDAO and user interface
public class UserService {
    //Init
    private UserDAO userDao = new UserDAO();

    public void addUser(String username, String password, String email, String role)
    {
        userDao.addUser(username, password, email, role);
    }

    public List<String> viewAllUsers()
    {
       return userDao.viewAllUsers();
    }

    public void deleteUser(int userID)
    {
        userDao.deleteUser(userID);
    }
}
