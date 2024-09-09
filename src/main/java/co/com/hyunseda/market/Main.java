
package co.com.hyunseda.market;

import co.com.hyunseda.market.presentation.GUIProducts;
import co.com.hyunseda.market.service.CategoryService;
import co.com.hyunseda.market.service.Factory;
import co.com.hyunseda.market.service.ICategoryService;
import co.com.hyunseda.market.service.IProductService;
import co.com.hyunseda.market.service.ProductService;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IProductService repoPro = Factory.getInstance().getRepository("default");
        ICategoryService repoCat = (ICategoryService) Factory.getInstance().getRepository("default");
        ProductService productService = new ProductService(repoPro);
        CategoryService catService = new CategoryService(repoCat);
        GUIProducts instance = new GUIProducts(productService,catService);
        instance.setVisible(true);
    }
    
}
