package crm.irfan;

public class User {
    private String  name;
    private String  surname;
    private String  username;
    private String  password;
    private Integer admin;
    public User() {
    }
    
    public User(String name, String surname, String username, String password, Integer admin) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
    
    public Integer isAdmin() {
        return admin;
    }
    
    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getFullName() {
        return this.name + " " + this.surname;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
