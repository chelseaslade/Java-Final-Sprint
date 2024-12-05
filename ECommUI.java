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
            case 2: 

            //Exit
            case 3:
            mainLoop = false;
            break;
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
