public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int sellerId;

    //constructors
    public Product(String name, double price, int quantity, int sellerId){
        this.name = name;
        this.price = price; 
        this.quantity = quantity; 
        this.sellerId = sellerId;
    }

    //getters and setters
    public int getId(){
        return this.id; 
    }
    public void setId(int id){
        this.id = id; 

    }
    public String getName(){
        return this.name; 
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public int getQuantity(){
        return quantity; 
    }
    public void setQuantity (int quantity){
        this.quantity = quantity;
    }
    public int getSellerId(){
        return this.sellerId;
    }
    public void setSellerId(int sellerId){
        this.sellerId = sellerId;
    }
}
