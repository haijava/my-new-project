package hai.exam1.service;

import hai.exam1.model.Category;
import hai.exam1.model.ImageView;
import hai.exam1.model.Product;
import org.springframework.data.repository.query.Param;

public interface ImageViewServices {
    Iterable<ImageView>findAll();
    ImageView findById(Long id);
    void save(ImageView imageViewy);
    void delete(Long id);
    Iterable<ImageView> findAllByProduct(Product product);
}
