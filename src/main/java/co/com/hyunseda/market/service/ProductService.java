package co.com.hyunseda.market.service;



import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class ProductService {
      
      private IProductService Data;
    
    /**
     * Constructor que inicia la base de datos
     * @author Libardo, Julio
     * @param data
     */ 
     public ProductService(IProductService data) {
        this.Data = data;
        
    }
    

    public boolean saveProduct(String name, String description, Category cat) {
        
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        
        //Validate product
        if (newProduct.getName().isEmpty() ) {
            return false;
        }

        return Data.save(newProduct,cat.getCategoryId());

    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = Data.findAll();

        return products;
    }
    
    public Product findProductById(Long id){
         
        return Data.findById(id);
    }
    
    public boolean deleteProduct(Long id){
        return Data.delete(id);
    }

    public boolean editProduct(Long productId, Product prod) {
        
        //Validate product
        if (prod == null || prod.getName().isEmpty() ) {
            return false;
        }
        return Data.edit(productId, prod);
    }

    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        products = Data.findByName(name);
        return products;
    }
    
    public List<Product> findAllCat(){
        return Data.findAllCat();
    }
    
    public List<Product> findByCat(long catId){
        return Data.findByCat(catId);
    }
}
