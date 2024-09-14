package co.com.hyunseda.market.domain;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class Product {


    private Long productId;

    private String name;
    
    private String description;
    
    private double price;
    
    private Category category;
    
    private Location location;
    
    private User user;

    public Product() {
    }
    public Product(String name, String description){
        this.name = name;
        this.description = description;
    }
    public Product(String name, String description,Category cat) {
        this.name = name;
        this.description = description;
         this.category = cat;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean equals(Product pro){
        return productId == pro.productId;
    }
    
    public boolean equalsCat(Product pro){
        return category.getCategoryId()== pro.category.getCategoryId();
    }

}
