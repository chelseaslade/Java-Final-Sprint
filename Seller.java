
public class Seller extends User {

    //Constructors
    public Seller(String username, String password, String email)
    {
        super(username, password, email, "seller");
    }

//     public void viewOwnedProducts(){
//         List<Product> products = ProductDAO.getProductsBySeller(this.getId());
//         if (products.isEmpty()) {
//             System.out.println("Error: 0 listed any products.");
//         } else {
//         for (Product product : products) {
//             System.out.println(product);          
//         }
//     }
// }

}
