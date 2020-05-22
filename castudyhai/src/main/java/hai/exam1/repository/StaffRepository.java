package hai.exam1.repository;

import hai.exam1.model.Staff;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StaffRepository extends PagingAndSortingRepository<Staff,Long> {
}
