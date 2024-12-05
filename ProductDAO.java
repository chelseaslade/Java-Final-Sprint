import java.sql.*;
import java.util.ArrayLists;
import java.util.List;

public class ProductDAO {
//ADD DB CONNECTIONS 




//PRODUCT: 
public static boolean addProduct(Product product){
    String query = "INSERT INTO products(name, price, quantity, seller_id) VALUES(?,?,?,?)";
    try (PreparedStatement statement = con.prepareStatement(query)){
        statement.setString(1, product.getName());
        statement.setBigDecimal(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getSellerId());
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0 ;
    } catch(SQLException e){
        e.printStackTrace();
        return false;
    }
}

// UPDATE PRODUCT DETAIL
public static boolean updateProduct(Product product) {
    String query = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, product.getName());
        statement.setBigDecimal(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getId());
        int rowsUpdated = statement.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
// DELETE PRODUCT
public static boolean deleteProduct(int productId) {
    String query = "DELETE FROM products WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, productId);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}
