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
}
