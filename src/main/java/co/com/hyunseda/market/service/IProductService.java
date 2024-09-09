/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Product;
import java.util.List;

/**
 *
 * @author Ashlee Campaz
 */
public interface IProductService {
    
     boolean save(Product newProduct, long catId);
     List<Product> findAll();
     List<Product> findAllCat();
     List<Product> findByCat(long catId);
     public List<Product> findByName(String name);
     boolean edit(Long id, Product product);
     Product findById(Long id);
     boolean delete(Long id);
     
}
