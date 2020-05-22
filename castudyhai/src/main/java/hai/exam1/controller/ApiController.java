/*
package hai.exam1.controller;

import hai.exam1.model.Product;
import hai.exam1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllCustomers(int min, int max) {
        List<Product> products = (List<Product>) productService.findByMinMax(min,max);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
}
*/
