package hai.exam1.repository;

import hai.exam1.model.ReceiptItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReceiptItemRepository extends PagingAndSortingRepository<ReceiptItem,Long> {
}
