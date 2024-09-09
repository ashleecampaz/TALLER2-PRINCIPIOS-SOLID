/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;
import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.infra.Subject;
import java.util.List;
/**
 *
 * @author Ashlee Campaz
 */
public class CategoryService extends Subject{
    
    private ICategoryService Data; 

    public CategoryService(ICategoryService Data) {
        this.Data = Data;
    }
    
    public boolean saveCategory(String name) {
        
        Category newCategory = new Category();
        newCategory.setName(name);
        //Validate category
        if (newCategory.getName().isEmpty() ) {
            return false;
        }
        
        return Data.save(newCategory);
    }
    
     public boolean deleteProduct(Long id){
        
        return Data.deleteCat(id);
    }

    public boolean editProduct(Long productId, Category cat) {
        
        //Validate product
        if (cat == null || cat.getName().isEmpty() ) {
            return false;
        }
      
        return Data.edit(productId, cat);
    }
    
     public Category findCategoryById(Long id){
         
        return Data.findCatById(id);
    }
     
    public List<Category> ListaCategorias(){
           return Data.Listcategories();
    }
}
