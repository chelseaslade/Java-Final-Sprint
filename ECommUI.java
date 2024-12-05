//Imports
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ECommUI {
    public static void main(String[] args) {
        //Construct test objects (as needed)

        //Test DB Connection
        try (Connection con = DBConnection.getCon()) {
        if (con != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }
        } catch (SQLException e) {
        e.printStackTrace();
    }

        //Init scanner
        UserService userService = new UserService();
        Scanner sc = new Scanner(System.in);

        //Loop for initial menu needed ("Exit" option closes entire program)
        boolean mainLoop = true;
        while (mainLoop == true)
        {

        //Login/Register Menu Options
        System.out.println("Main Menu (Select by Number): " + "\n");
        //1. Register
        System.out.println("1: Register" + "\n");
        //2. Login
        System.out.println("2: Login" + "\n");
        //3. Exit
        System.out.println("3: Exit" + "\n");

        //Selection
        int choice = sc.nextInt();
        sc.nextLine();

        //Execute Options.
        switch (choice)
        {
            //Register
            case 1: 
            System.out.println("Enter username: ");
            String username = sc.nextLine();
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            System.out.println("Enter email: ");
            String email = sc.nextLine();
            System.out.println("Enter role (buyer, seller, or admin) ");
            String role = sc.nextLine();

            userService.addUser(username, password, email, role);

            //Login
            // Added menu options for login.
            case 2: 
            System.out.println("Enter username: ");
            String loginUsername = sc.nextLine();
            System.out.println("Enter password: ");
            String loginPassword = sc.nextLine();

            // Authenticating user roll.
            String roleLoggedIn = userService.authenticate(loginUsername, loginPassword); // Placeholder
            if (roleLoggedIn == null) {
                System.out.println("Login failed. Try again.");
            } else {
                System.out.println("Login successful! Welcome, " + roleLoggedIn);

                switch (roleLoggedIn.toLowerCase()) {
                    case "buyer":
                        showBuyerMenu(sc, userService); // Placeholder
                        break;
                    case "admin":
                        showAdminMenu(sc, userService); // Placeholder
                        break;
                    default:
                   System.out.println("Role not recognized.");
                }
            }
            break;

            //Exit
            case 3:
            mainLoop = false;
            break;
        }
    }
    }

    // Created Admin menu and options.
    private static void showAdminMenu(Scanner sc, UserService userService) {
    boolean adminLoop = true;

    while (adminLoop) {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1: View All Users");
        System.out.println("2: Delete a User");
        System.out.println("3: Logout");

        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1: 
                System.out.println("\n--- User List ---");
                userService.getAllUsers().forEach(System.out::println); // Placeholder
                break;

            case 2: 
                System.out.print("Enter User ID to delete: ");
                int userId = sc.nextInt();
                sc.nextLine(); 
                boolean success = userService.deleteUser(userId); // Placeholder
                if (success) {
                    System.out.println("User deleted successfully.");
                } else {
                    System.out.println("Failed to delete user. Check User ID.");
                }
                break;

            case 3:
                adminLoop = false;
                System.out.println("Logging out...");
                break;

            default:
                System.out.println("Invalid option. Try again.");
        }
    }



        //Loop for main menu needed ("Exit" option to take back to login)

        //Main Menu Options (Depends on user type logged in)
        //Admin Menu
        //1. View User List
        //2. View Product List
        //3. Logout

        //Buyer Menu
        //1. View All Products
        //2. Search Products
        //3. Logout

        //Seller Menu
        //1. View All Currently Listed Products
        //2. Add New Product
        //3. Update Currently Listed Product
        //4. Delete Currently Listed Product
        //5. Logout

    }
}
