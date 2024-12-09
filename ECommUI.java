//Imports
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ECommUI {
    public static void main(String[] args) {

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

            //Create object based on role 
            User user = null;
            if (role.equalsIgnoreCase("admin"))
            {
                user = new Admin(username, password, email);
            }
            else if (role.equalsIgnoreCase("buyer"))
            {
                user = new Buyer(username, password, email);
            }
            else if (role.equalsIgnoreCase("seller"))
            {
                user = new Seller(username, password, email);
            }

            //Add the user to database
            userService.addUser(user);

            //Query to check if added
            boolean isAdded = userService.isUserInDatabase(user.getUsername());
            if (isAdded)
            {
            //Success
            System.out.println("Registration successful!");
            break;
            }

            else 
            {
            //Failure
            System.out.println("Registration unsuccessful. Are you sure the user does not already exist?");
            }

            // Login
            case 2: 
               System.out.println("Enter username: ");
               String loginUsername = sc.nextLine();
               System.out.println("Enter password: ");
               String loginPassword = sc.nextLine();

            //    Placeholder for authentication logic.
                String roleLoggedIn = userService.authenticate(loginUsername, loginPassword); 
                    
                if (roleLoggedIn == null) {
                    System.out.println("Login failed. Try again.");
                } else {
                    System.out.println("Login successful! Welcome, " + roleLoggedIn);

                    switch (roleLoggedIn.toLowerCase()) {
                        case "buyer":
                            showBuyerMenu(sc, productService);
                            break;

                        case "seller":
                            int sellerID = userService.getSellerIdByUsername(loginUsername);
                            showSellerMenu(sc, productService, sellerID);
                            break;

                        case "admin":
                            showAdminMenu(sc, userService, productService);
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
    //Close scanner
    sc.close();
    }

   // Admin Menu
   private static void showAdminMenu(Scanner sc, UserService userService, ProductService productService) {
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
                productService.viewAllProducts().forEach(System.out::println); 
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
    private static void showBuyerMenu(Scanner sc, ProductService productService) {
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
                    productService.viewAllProducts().forEach(System.out::println); 
                    break;
                case 2:
                    System.out.println("Enter search type ('name', 'product_id, or 'seller_id': ");
                    String searchType = sc.nextLine();
                    System.out.print("Enter search query: ");
                    String searchQuery = sc.nextLine();
                    List<String> searchResults = productService.searchProducts(searchType, searchQuery);

                    //If no products returned
                    if (searchResults.isEmpty())
                    {
                        System.out.println("No products found for the search query provided.");
                    }
                    else
                    {
                        searchResults.forEach(System.out::println); //Print each result found
                    }
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

    //Created Seller menu with options
    private static void showSellerMenu(Scanner sc, ProductService productService, int sellerID) {

        boolean sellerLoop = true;

        while (sellerLoop) {
        System.out.println("\n--- Seller Menu ---");
        System.out.println("1: View My Products");
        System.out.println("2: Add New Product");
        System.out.println("3: Update Product");
        System.out.println("4: Delete Product");
        System.out.println("5: Logout");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1: 
                System.out.println("\n--- Your Products ---");

                //Find products by sellerID (convert from int to string for search)
                String sellerIDString = String.valueOf(sellerID);

                //Search products and print list
                productService.searchProducts("seller_id", sellerIDString).forEach(System.out::println); 
                break;

            case 2: 
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                System.out.print("Enter product price: ");
                double price = sc.nextDouble();
                System.out.print("Enter product quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine(); // Consume newline

                //Add details to a new product
                Product newProduct = new Product(name, price, quantity, sellerID);

                //Add new product to database
                productService.addProduct(newProduct); 
                System.out.println("Product added successfully!");
                break;

            case 3: 
                System.out.print("Enter Product ID to update: ");
                int productID = sc.nextInt();
                sc.nextLine(); 

                Product currentProduct = productService.getProductByID(productID);
                if (currentProduct == null) {
                    System.out.println("Product not found. Please check the Product ID.");
                    break; // Exit if the product does not exist
                }

                // Display the current details
                System.out.println("Current Product Details: ");
                System.out.println("Name: " + currentProduct.getName());
                System.out.println("Price: " + currentProduct.getPrice());
                System.out.println("Quantity: " + currentProduct.getQuantity());

                //Get new product details
                System.out.println("Enter new name: ");
                String newName = sc.nextLine();
                System.out.print("Enter new price: ");
                double newPrice = sc.nextDouble();
                System.out.print("Enter new quantity: ");
                int newQuantity = sc.nextInt();
                sc.nextLine(); 

                //Product object with updated details
                Product productUpdate = new Product(newName, newPrice, newQuantity, sellerID);

                //Set product ID
                productUpdate.setId(productID);

                //Update product in database
                productService.updateProduct(productUpdate); 

                // Validate the update
                if (productUpdate != null &&
                productUpdate.getName().equals(newName) &&
                productUpdate.getPrice() == newPrice &&
                productUpdate.getQuantity() == newQuantity) 
                {
                    System.out.println("Product updated successfully!");
                } else {
                    System.out.println("Failed to update product. Please try again.");
                }
                break;

            case 4: 
                System.out.print("Enter Product ID to delete: ");
                int deleteProductId = sc.nextInt();
                sc.nextLine(); 

                //Delete item from database
                productService.deleteProduct(deleteProductId);
            
                //Check if item is in database
                boolean productInDatabase = productService.isProductInDatabase(deleteProductId); 

                if (!productInDatabase) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println("Failed to delete product. Check Product ID.");
                }
                break;

            case 5: // Logout
                sellerLoop = false;
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
        }
    }
    }

