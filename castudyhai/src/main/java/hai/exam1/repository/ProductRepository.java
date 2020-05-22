package hai.exam1.repository;

import hai.exam1.model.Category;
import hai.exam1.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByNameContains(String name);


    @Query(value = "SELECT * FROM exam11.products where price <=6000000 and price >= 3000000;", nativeQuery = true)
    Iterable<Product> listProduct_3milion_6milion();

    @Query(value = "SELECT * FROM exam11.products where price <=9000000 and price >= 6000000;", nativeQuery = true)
    Iterable<Product> listProduct_6milion_9milion();

    @Query(value = "SELECT * FROM exam11.products where price <=22000000 and price >9000000;", nativeQuery = true)
    Iterable<Product> listProduct_9milion_22milion();

    @Query(value = "SELECT * FROM exam11.products where (price <= 6000000 and price >=3000000) and category_id=?;", nativeQuery = true)
    Iterable<Product> listProduct_price_3milion_6milion(@Param("id") Long id);
    @Query(value = "SELECT * FROM exam11.products where (price <=9000000 and price > 6000000)and category_id=?;", nativeQuery = true)
    Iterable<Product> listProduct_price_6milion_9milion(@Param("id") Long id);

    @Query(value = "SELECT * FROM exam11.products where (price <=22000000 and price >9000000)and category_id=?;", nativeQuery = true)
    Iterable<Product> listProduct_price_9milion_22milion(@Param("id") Long id);

    @Query(value = "SELECT * FROM exam11.products where name like ? and category_id =?;", nativeQuery = true)
    Iterable<Product> listProduct_name(@Param("id") Long id,@Param("name") String name);
    @Query(value = "SELECT * FROM exam11.products where price > ?-1000000 and price < ?+1000000;", nativeQuery = true)
    Iterable<Product> listProduct_price(@Param("pr") Long pr,@Param("pr") Long pl);

}
