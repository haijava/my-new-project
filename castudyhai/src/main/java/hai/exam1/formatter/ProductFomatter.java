package hai.exam1.formatter;

import hai.exam1.model.Product;
import hai.exam1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@Component
public class ProductFomatter implements Formatter<Product> {
    private ProductService productService;

    @Autowired
    public ProductFomatter(ProductService productService){
        this.productService=productService;
    }

    @Override
    public Product parse(String text, Locale locale) throws ParseException {
        return productService.findId(Long.parseLong(text));
    }

    @Override
    public String print(Product object, Locale locale) {
        return "[" + object.getId()+ "," +object.getName()+ "]";
    }

}
