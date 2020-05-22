package hai.exam1.repository;

import hai.exam1.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepositotry extends PagingAndSortingRepository<Category,Long> {
}
