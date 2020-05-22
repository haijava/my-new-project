package hai.exam1.repository;

import hai.exam1.model.Status;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatusRepository extends PagingAndSortingRepository<Status,Long> {
}
