
public class Admin extends User {
    //Constructors
    public Admin(String username, String Password, String email){
        super(username, password, email, "admin");
    }
    //Methods
    public void viewAllUsers(){
        List<User> users = UserDAO.getAllUsers();
        if (users.isEmpty()){
            System.out.println("Error: Users not found");
        } else{
            for (User user: users){
                System.out.println(user:) // will display user details
            }
        }
    }
    public void deleteUser(int userId){
        if (UserDAO.deleteUser(userId)){
            System.out.println("User deleted"); 
        } else{
            System.out.println("Error: failed to delete (user)");
        }
    }
    public void viewAllProducts(){
        List<Product> products = ProductDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Error: Product not listed.");
        } else {
        for (Product product : products) {
            System.out.println(product);  // displays product detail. 
        }
    }
    }
    public void deleteProduct(int productId){
        if (ProductDAO.deleteProduct(productId)) {
            System.out.println("Product deleted.");
        } else {
            System.out.println("Error: failed to delete product.");
    }
}
