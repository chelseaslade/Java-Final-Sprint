public class Product {
    private int id;
    private String name;
    private BigDecimal price; //bigdecimal for precise amt/$
    private int quantity;
    private int sellerId;

    //constructors
    public Product(String name, BigDecimal price, int quantity, int sellerId){
        this.name = name;
        this.price = price; 
        this.quantity = quantity; 
        this.sellerId = sellerId;
    }

    //getters and setters
    public int getId(){
        return id; 
    }
    public void setId(int id){
        this.id = id; 

    }
    public String getName(){
        return name; 
    }
    public void setName(String name){
        this.name = name
    }
    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = getPrice;
    }
    public int getQuantity(){
        return quantity; 
    }
    public void setQuantity (int quantity){
        this.quantity = quantity;
    }
    public in getSellerId(){
        return sellerId;
    }
    public void setSellerId(int sellerId){
        this.sellerId = sellerId;
    }
}
