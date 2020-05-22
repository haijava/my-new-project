package hai.exam1.repository;

import hai.exam1.model.Receipt;
import hai.exam1.model.Stastical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ReceiptRepository extends PagingAndSortingRepository<Receipt,Long> {
    @Query ("SELECT new hai.exam1.model.Stastical(r.receiptDate, count (r.receiptStatus)) " +
            "FROM Receipt r " +
            "WHERE r.receiptDate >=:begin AND r.receiptDate <:end " +
            "GROUP BY r.receiptDate")
    Iterable<Stastical> statiscal(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
    /*@Query ("SELECT new hai.exam1.model.Stastical(r.receiptDate, count (r.receiptStatus)) " +
            "FROM Receipt r " +
            "WHERE r.receiptDate >=:begin AND r.receiptDate <:end " +
            "GROUP BY r.receiptDate")
    Iterable<Stastical> statiscalProduct(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);*/
}
