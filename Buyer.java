
public class Buyer extends User  {
    //Constructors
    public Buyer(String username, String password, String email){
        super(username, password, email, "buyer");
    }
    //Getters & Setters
    @Override
    public String getUsername(){
        return super.getUsername
    }

    @Override
    public void setUsername(String username){
        return super.setUsername
    }

    @Override
    public String getPassword(){
        return super.getPassword();
    }
    @Override
    public void setPassword(String password){
        super.setPassword(password);
    }

    @Override 
    public String getEmail(){
        return super.getEmail();
    }
    
    @Override
    public void setEmail(String email){
        super.setEmail(email);
    }

    @Override
    public String getRole(){
        return super.getRole();
    }
    
    @Override
    public void setRole(String role){
        super.setRole(role);
    }
}
