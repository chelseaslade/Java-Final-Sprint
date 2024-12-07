import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//ProductService is an intermediary between ProductDAO and user interface
public class ProductService {
    //Init
    private ProductDAO productDao = new ProductDAO();

    public List<String> viewAllProducts()
    {
        return productDao.viewAllProducts();
    }

    public void addProduct(Product product)
    {
        productDao.addProduct(product);
    }

    public void updateProduct(Product product)
    {
        productDao.updateProduct(product);
    }
    
    public void deleteProduct(int productId)
    {
        productDao.deleteProduct(productId);
    }

    public List<String> searchProducts(String searchType, String searchQuery)
    {
        return productDao.searchProducts(searchType, searchQuery);
    }

    public boolean isProductInDatabase(int productID)
    {
                try 
        {
        return productDao.isProductInDatabase(productID);
    }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Product getProductByID(int productID) {
    String query = "SELECT * FROM Products WHERE id = ?";
    try (
        Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query)) 
    {
        statement.setInt(1, productID);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new Product(
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getInt("seller_id")
            );
        }
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Return null if no product is found
}
}
