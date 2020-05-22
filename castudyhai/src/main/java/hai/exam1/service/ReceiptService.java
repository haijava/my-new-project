package hai.exam1.service;

import hai.exam1.model.Receipt;
import hai.exam1.model.Stastical;

public interface ReceiptService {
    Iterable<Receipt> findAll();
    Receipt findId(Long id);
    void save(Receipt receipt);
    void delete(Long id);
}
