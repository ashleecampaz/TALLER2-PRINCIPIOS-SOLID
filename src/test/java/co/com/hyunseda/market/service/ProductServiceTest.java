/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.Factory;
import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;


/**
 *
 * @author Ashlee Campaz
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 public class ProductServiceTest {
    
    private  ProductService instance;
    private  List<Product> productos;
    private  IProductService repoPro;
    
    public ProductServiceTest() {
         repoPro = Factory.getInstance().getRepository("default");
        instance =   new ProductService(repoPro);
        productos = new ArrayList<>();
    }
    
    @BeforeAll
    public  void setUpClass() {    
        Category cat = new Category((long) 2,"Lacteos");
        repoPro.save(new Product("Yogurt", "alpina"), 2L);
        repoPro.save(new Product("Avena", "sanfernando"), 2L);
        productos.add(new Product("Yogurt", "alpina", cat));
        productos.add(new Product("Avena", "sanfernando", cat));
        System.out.println("ESTOY AQUIIII ");  
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("FIN TEST ");  
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of saveProduct method, of class ProductService.
     */
    @Test
    public void testSaveProduct() {
        System.out.println("saveProduct");

        String name = "cloro";
        String description = "patojito";
        Category cat = new Category((long) 1,"Ninguna");
        boolean result = instance.saveProduct(name, description, cat);
        productos.add(new Product(name, description, cat));
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findAllProducts method, of class ProductService.
     */
    @Test
    public void testFindAllProducts() {
        System.out.println("findAllProducts");
        List<Product> result = instance.findAllProducts();
        assertEquals( result.size(),productos.size());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findProductById method, of class ProductService.
     */
    @Test
    public void testFindProductById() {
        System.out.println("findProductById");
        //agrego un producto 
        Long id = (long) productos.size()-1;
        Product result = instance.findProductById(id);
        assertEquals(result.getProductId(),id);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of deleteProduct method, of class ProductService.
     */
    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");
        Long id = (long)1;
        boolean result = instance.deleteProduct(id);
        productos.remove(0);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of editProduct method, of class ProductService.
     */
    @Test
    public void testEditProduct() {
        System.out.println("editProduct");

        Long productId = (long)productos.size()-1;
        Product prod = productos.get( productId.intValue());
        prod.setDescription("algo diferente");
        boolean result = instance.editProduct(productId, prod);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findByName method, of class ProductService.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String namesearch = "Av";
        List<Product> result = instance.findByName(namesearch);
        assertTrue(result.get(0).getName().contains(namesearch));
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findAllCat method, of class ProductService.
     */
    @Test
    public void testFindAllCat() {
        System.out.println("findAllCat");
        
        List<Product> result = instance.findAllCat();
        for(Product pro : productos){
            assertTrue(pro.getCategory()!=null);
        }
    }

    /**
     * Test of findByCat method, of class ProductService.
     */
    @Test
    public void testFindByCat() {
        System.out.println("findByCat");
        long catId = 2L;
        List<Product> result = instance.findByCat(catId);
        for (Product pro: result ){
             assertEquals(pro.getCategory().getCategoryId(),catId);
        }
       
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
