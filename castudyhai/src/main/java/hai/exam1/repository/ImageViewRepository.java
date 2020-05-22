package hai.exam1.repository;

import hai.exam1.model.ImageView;
import hai.exam1.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ImageViewRepository extends PagingAndSortingRepository<ImageView,Long> {

    Iterable<ImageView> findAllByProduct(Product product);

}
