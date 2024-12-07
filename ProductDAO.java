import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

//View all products
public List<String> viewAllProducts()
{
    List<String> products = new ArrayList<>();
    String query = "SELECT * FROM Products";

    try (Connection con = DBConnection.getCon();
    PreparedStatement statement = con.prepareStatement(query);
    ResultSet rs = statement.executeQuery();)
    {
        while (rs.next())
        {
            products.add(rs.getString("name"));
        }
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    return products;
}

// Add product
public void addProduct(Product product)
{
    String query = "INSERT INTO Products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
    try (Connection con = DBConnection.getCon();
    PreparedStatement statement = con.prepareStatement(query))
    {
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getSellerId());
        statement.executeUpdate();
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
}

// UPDATE PRODUCT DETAIL
public void updateProduct(Product product) 
{
    String query = "UPDATE Products SET name = ?, price = ?, quantity = ? WHERE id = ?";
    try (
        Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query)) 
        {
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getId());
        statement.executeUpdate();
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
}

// DELETE PRODUCT
public void deleteProduct(int productID) {
    String query = "DELETE FROM Products WHERE id = ?";
    try (
        Connection con = DBConnection.getCon();
        PreparedStatement statement = con.prepareStatement(query)) 
        {
        statement.setInt(1, productID);
        statement.executeUpdate();
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
}

//Search Products
public List<String> searchProducts(String searchType, String searchQuery)
{
    List<String> productSearch = new ArrayList<>();
    String query = "";

    //Query based on search type
    if ("name".equalsIgnoreCase(searchType))
    {
        query = "SELECT * FROM Products WHERE name ILIKE ?";
    }
    else if ("product_id".equalsIgnoreCase(searchType))
    {
        query = "SELECT * FROM Products WHERE product_id = ?";
    }
    else if ("seller_id".equalsIgnoreCase(searchType))
    {
        query = "SELECT * FROM Products where seller_id = ?";
    }
    else 
    {
        System.out.println("Invalid search type selection, please select from 'name', 'product_id', or 'seller_id'.");
        return productSearch;
    }

    try (Connection con = DBConnection.getCon();
    PreparedStatement statement = con.prepareStatement(query);)
    {
        //Set search query
        if ("name".equalsIgnoreCase(searchType))
        {
            statement.setString(1, "%" + searchQuery + "%"); //Allows partial matches
        }
        else 
        {
            statement.setInt(1, Integer.parseInt(searchQuery)); //Search by int for seller and product id
        }

        //Execute Query
        try (ResultSet rs = statement.executeQuery())
        {
            while (rs.next())
            {
                //Display product details
                String productDetails = String.format("Product ID: %d, Name: %s, Price: %.2f, Quantity: %d, Seller ID: %d",
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getInt("seller_id")
                );
                //Add product details to productSearch list to be returned
                productSearch.add(productDetails);
            }
        }

    }

    catch(SQLException e)
    {
        e.printStackTrace();
    }
    return productSearch;
}

//Product in database validation
public boolean isProductInDatabase(int productID) throws SQLException
{
    String query = "SELECT COUNT(*) FROM Products WHERE product_id = ?";

    try (Connection con = DBConnection.getCon();
    PreparedStatement statement = con.prepareStatement(query)) {
    
    statement.setInt(1, productID);
    try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0; // Return true if count > 0
        }
    }
}
return false;
}

}
