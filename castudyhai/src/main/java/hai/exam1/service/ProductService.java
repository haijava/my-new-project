package hai.exam1.service;

import hai.exam1.model.Category;
import hai.exam1.model.Product;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface ProductService {
    Iterable<Product> findAll();
    Product findId(Long id);
    void save(Product product);
    void delete(Long id);
    Iterable<Product> findByCategory(Category category);
    Iterable<Product>findAllByNameContains(String name);
   /* Iterable<Product>findTopByOrderByPriceDesc(Long price);*/
   Iterable<Product> listProduct_3milion_6milion();
    Iterable<Product> listProduct_6milion_9milion();
    Iterable<Product> listProduct_9milion_22milion();
    Iterable<Product> listProduct_price_3milion_6milion(@Param("id")Long id);
    Iterable<Product> listProduct_price_6milion_9milion(@Param("id")Long id);
    Iterable<Product> listProduct_price_9milion_22milion(@Param("id")Long id);
    Iterable<Product> listProduct_name(@Param("id") Long id,@Param("name") String name);
    Iterable<Product> listProduct_price(@Param("pr") Long pr,@Param("pr") Long pl);

}
