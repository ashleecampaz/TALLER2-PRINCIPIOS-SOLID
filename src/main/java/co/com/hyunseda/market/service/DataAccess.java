/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashlee Campaz
 */
public class DataAccess implements IProductService, ICategoryService {
    
    private Connection conn;
    
    public DataAccess() { 
        initDatabase();
        initializeCategories();
    }
    
    
    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	productId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name text NOT NULL,\n"
                + "	description text NULL,\n"
                +"	categoryId integer NULL\n"
                + ");";
        String sql2= "CREATE TABLE IF NOT EXISTS category (\n"
                + "     categoryId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "     catname text NOT NULL\n);";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.execute(sql2);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public boolean save(Product newProduct, long catId) {

        try {
            //Validate product
            if (newProduct == null || newProduct.getName().isEmpty()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO products ( name, description,categoryId  ) "
                    + "VALUES ( ?, ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newProduct.getName());
            pstmt.setString(2, newProduct.getDescription());
            pstmt.setLong(3, catId);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));

                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
    @Override
    public boolean edit(Long id, Product product) {
        try {
            //Validate product
            if (id <= 0 || product == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  products "
                    + "SET name=?, description=? "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setLong(3, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        try {

            String sql = "SELECT * FROM products INNER JOIN category "
                    + "ON products.categoryId = category.categoryId "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Product prod = new Product();
                prod.setProductId(res.getLong("productId"));
                prod.setName(res.getString("name"));
                prod.setDescription(res.getString("description"));
                Category newCategory = new Category();
                newCategory.setCategoryId(res.getLong("categoryId"));
                newCategory.setName(res.getString("catname"));
                prod.setCategory(newCategory);
               
                return prod;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products "
                    + "INNER JOIN category ON products.categoryId = category.categoryId "
                    + "WHERE name LIKE ?";
            //this.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getLong("categoryId"));
                newCategory.setName(rs.getString("catname"));
                newProduct.setCategory(newCategory);
                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public boolean save(Category newCategory) {
        try {
            //Validate product
            if (newCategory == null || newCategory.getName().isEmpty()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO category ( catname ) "
                    + "VALUES ( ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCategory.getName());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean edit(Long id, Category category) {
        try {
            //Validate product
            if (id <= 0 || category == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  category "
                    + "SET catname=? "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteCat(Long id) {
        try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM category "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Category findCatById(Long id) {
        try {

            String sql = "SELECT * FROM category  "
                    + "WHERE categoryId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category cat = new Category();
                cat.setCategoryId(res.getLong("categoryId"));
                cat.setName(res.getString("catname"));
 
                return cat;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void initializeCategories() {
        String categorias[] = {"Ninguna","Lacteos","Granos","Dulces"}; 
        
        for(String cat: categorias){
            save(new Category(cat));
        }
    }

    @Override
    public List<Category> Listcategories() {
        List<Category> categories = new ArrayList<>();
        try {

            String sql = "SELECT * FROM category";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getLong("categoryId"));
                newCategory.setName(rs.getString("catname"));
                categories.add(newCategory);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    @Override
    public List<Product> findAllCat() {
        List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products INNER JOIN category ON "
                    + "products.categoryId = category.categoryId";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                Product newProduct = new Product();

                newProduct.setProductId(rs.getLong("productId"));
                newProduct.setName(rs.getString("name"));
                newProduct.setDescription(rs.getString("description"));
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getLong("categoryId"));
                newCategory.setName(rs.getString("catname"));
                newProduct.setCategory(newCategory);
                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
    
}
