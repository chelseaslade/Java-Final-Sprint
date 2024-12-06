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

        //UserService init
        UserService userService = new UserService(); 

        //ProductService init
        ProductService productService = new ProductService(); 

        //Scanner init
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

        //Execute Options
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

            //Add the user to database
            userService.addUser(username, password, email, role);

            // Message showing registration has been successful.
            System.out.println("Registration successful!");
            break;

            //Login
            case 2: 
               System.out.println("Enter username: ");
               String loginUsername = sc.nextLine();
               System.out.println("Enter password: ");
               String loginPassword = sc.nextLine();

               // Placeholder for authentication logic.
                String roleLoggedIn = userService.authenticate(loginUsername, loginPassword); 
                    
                if (roleLoggedIn == null) {
                    System.out.println("Login failed. Try again.");
                } else {
                    System.out.println("Login successful! Welcome, " + roleLoggedIn);
                     // Add similar menus for "seller" and "admin" roles later
                     // Added
                    switch (roleLoggedIn.toLowerCase()) {
                        case "buyer":
                            showBuyerMenu(sc, productDAO);
                            break;
                        case "seller":
                            showSellerMenu(sc, productDAO);
                            break;
                        case "admin":
                            showAdminMenu(sc, userService, productDAO);
                            break;
                        default:
                        System.out.println("Invalid role.");
                        
                        }

                    }
                    break;


            //Exit
            case 3:
            mainLoop = false;
            // Message showing the user has exited.
            System.out.println("Goodbye!");
            break;
            
            // Default message incase user input is incorrect.
            default:
            System.out.println("Invalid option. Please try again.");
        }
    }

    sc.close();

    }

   // Admin Menu
   private static void showAdminMenu(Scanner sc, UserService userService, ProductDAO productDAO) {
    boolean adminLoop = true;
    while (adminLoop) {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1: View All Users");
        System.out.println("2: Delete User");
        System.out.println("3: View All Products");
        System.out.println("4: Logout");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                userService.viewAllUsers().forEach(System.out::println); 
                break;
            case 2:
                System.out.print("Enter User ID to delete: ");
                int userId = sc.nextInt();
                sc.nextLine();
                userService.deleteUser(userId); 
                System.out.println("User deleted successfully.");
                break;
            case 3:
                productService.viewAllProducts().forEach(System.out::println); // Placeholder
                break;
            case 4:
                adminLoop = false;
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }
}

    // Buyer menu
    private static void showBuyerMenu(Scanner sc, ProductDAO productDAO) {
        boolean buyerLoop = true;
        while (buyerLoop) {
            System.out.println("\n--- Buyer Menu ---");
            System.out.println("1: View All Products");
            System.out.println("2: Search Products");
            System.out.println("3: Logout");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- Product List ---");
                    productService.viewAllProducts().forEach(System.out::println); // Placeholder.
                    break;
                case 2:
                    System.out.print("Enter product name or category: ");
                    String keyword = sc.nextLine();
                    productService.searchProducts(keyword).forEach(System.out::println); // Placeholder.
                    break;
                case 3:
                    buyerLoop = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    }

