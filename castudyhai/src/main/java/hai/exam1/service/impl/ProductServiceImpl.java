package hai.exam1.service.impl;

import hai.exam1.model.Category;
import hai.exam1.model.Product;
import hai.exam1.repository.ProductRepository;
import hai.exam1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findId(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);

    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Iterable<Product> findByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Product> findAllByNameContains(String name) {
        return productRepository.findAllByNameContains(name);
    }

    @Override
    public Iterable<Product> listProduct_3milion_6milion() {
        return productRepository.listProduct_3milion_6milion();
    }

    @Override
    public Iterable<Product> listProduct_6milion_9milion() {
        return productRepository.listProduct_6milion_9milion();
    }

    @Override
    public Iterable<Product> listProduct_9milion_22milion()  {
        return productRepository.listProduct_9milion_22milion();
    }

    @Override
    public Iterable<Product> listProduct_price_3milion_6milion(Long id) {
        return productRepository.listProduct_price_3milion_6milion(id);
    }

    @Override
    public Iterable<Product> listProduct_price_6milion_9milion(Long id) {
        return productRepository.listProduct_price_6milion_9milion(id);
    }

    @Override
    public Iterable<Product> listProduct_price_9milion_22milion(Long id) {
        return productRepository.listProduct_price_9milion_22milion(id);
    }

    @Override
    public Iterable<Product> listProduct_name(Long id,String name) {
        return productRepository.listProduct_name(id,name);
    }

    @Override
    public Iterable<Product> listProduct_price(Long pr,Long pl) {
        return productRepository.listProduct_price(pr,pl);
    }




  /*  @Override
    public Iterable<Product> findTopByOrderByPriceDesc(Long price) {
        return productRepository.findAllByNameOrderByPriceAsc(price);
    }

   *//* @Override
    public Iterable<Product> findAllByPrice(Long price) {
        return productRepository.findAllByNameAndOrderByPriceDesc(price);
    }*//*
*/

}

