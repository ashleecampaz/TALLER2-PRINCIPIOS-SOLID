/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.hyunseda.market.service;
//import co.com.hyunseda.market.domain.Category;

import co.com.hyunseda.market.domain.Category;
import java.util.List;



/**
 *
 * @author Ashlee Campaz
 */
public interface ICategoryService {
    boolean save(Category newCategory);
    boolean deleteCat(Long id);
    boolean edit(Long id, Category category);
    Category findCatById(Long id);
    void initializeCategories();
    List<Category> Listcategories();
}
