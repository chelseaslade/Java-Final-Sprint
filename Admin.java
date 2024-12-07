
public class Admin extends User {
    //Constructors
    public Admin(String username, String password, String email){
        super(username, password, email, "admin");
    }

    //getters & setters
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getRole() {
        return super.getRole();
    }

    @Override
    public void setRole(String role) {
        super.setRole(role);
    }
    
    
    /* ADMIN METHOD - REVIEWED ECommUI SAW ACTION THERE
        // delete user 
    public void deleteUser(User user, UserDAO userDAO){
       userDAO.deleteUser(user);
    }
        //return user
    public void viewAllUsers(UserDAO userDAO){
        userDAO.getAllUsers().forEach(user -> System.out.println(getUsername()+ "-" + user.getEmail()))
    }
        // view products
    public void viewAllProducts(ProductDAO productDAO){
        productDAO.getAllProducts().forEach(product->System.out.println(product.getName()+ "-" +product.getPrice()))
    } */


 