
public class Seller extends User {

    Public Seller(String username, String password, String email)
    {
        super(username, password, email, "seller");
    }

    //Methods
    public void addProduct (String name, BigDecimal price, int quantity){
        if (ProductDAO.addProduct(product)) {
            System.out.println("Product added: " + product.getName());
        } else {
        System.out.println(" Error: Failed to add product.");
        }
    }
    public void updateProduct(int productId, String name, BigDecimal price, int quantity){
        if (ProductDAO.updateProduct(product)) {
            System.out.println("Product updated: " + product.getName());
        } else {
            System.out.println("Error: Failed to update product.");
        }
    }
    public void deleteProduct(int productId){
        if (ProductDAO.deleteProduct(productId)){
            System.out.println("Product deleted");
        }else{
            System.out.println("Error: Failed to delete product")
        }
    }
    public void viewOwnedProducts(){
        List<Product> products = ProductDAO.getProductsBySeller(this.getId());
        if (products.isEmpty()) {
            System.out.println("Error: 0 listed any products.");
        } else {
        for (Product product : products) {
            System.out.println(product);          
        }
    }
}
