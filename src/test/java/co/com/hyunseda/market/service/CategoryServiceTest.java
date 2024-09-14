/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.Factory;
import co.com.hyunseda.market.domain.Category;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ashlee Campaz
 */
public class CategoryServiceTest {
    
    public CategoryServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of saveCategory method, of class CategoryService.
     */
    @Test
    public void testSaveCategory() {
        System.out.println("saveCategory");
        String name = "papeleria";
        ICategoryService repoPro = (ICategoryService) Factory.getInstance().getRepository("default");
        CategoryService instance =  new CategoryService(repoPro);
        boolean result = instance.saveCategory(name);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of deleteProduct method, of class CategoryService.
     */
    @Test
    public void testDeleteCategory() {
        System.out.println("deleteProduct");
        String name = "papeleria";
        ICategoryService repoPro = (ICategoryService) Factory.getInstance().getRepository("default");
        CategoryService instance =  new CategoryService(repoPro);
        instance.saveCategory(name);
        Long id = 5L;
        boolean result = instance.deleteCategory(id);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of editProduct method, of class CategoryService.
     */
    @Test
    public void testEditCategory() {
        System.out.println("editProduct");
        String name = "papeleria";
        ICategoryService repoPro = (ICategoryService) Factory.getInstance().getRepository("default");
        CategoryService instance =  new CategoryService(repoPro);
        instance.saveCategory(name);
        Long id = 5L;
        boolean result = instance.editCategory(id, new Category("decoracion"));
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of findCategoryById method, of class CategoryService.
     */
    @Test
    public void testFindCategoryById() {
        System.out.println("findCategoryById");
        String name = "papeleria";
        ICategoryService repoPro = (ICategoryService) Factory.getInstance().getRepository("default");
        CategoryService instance =  new CategoryService(repoPro);
        instance.saveCategory(name);
        Long id = 5L;
        Category result = instance.findCategoryById(id);
        assertEquals(id,result.getCategoryId());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of ListaCategorias method, of class CategoryService.
     */
    @Test
    public void testListaCategorias() {
        System.out.println("ListaCategorias");
        ICategoryService repoPro = (ICategoryService) Factory.getInstance().getRepository("default");
        CategoryService instance =  new CategoryService(repoPro);
        List<Category> result = instance.ListaCategorias();
        assertEquals(4, result.size());
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
