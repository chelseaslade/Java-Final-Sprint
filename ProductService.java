import java.util.List;

//ProductService is an intermediary between ProductDAO and user interface
public class ProductService {
    //Init
    private ProductDAO productDao = new ProductDAO();

    public List<String> viewAllProducts()
    {
        return productDao.viewAllProducts();
    }
    
}
