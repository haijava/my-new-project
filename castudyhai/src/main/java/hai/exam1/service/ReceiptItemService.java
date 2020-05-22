package hai.exam1.service;

import hai.exam1.model.ReceiptItem;

public interface ReceiptItemService {
    Iterable<ReceiptItem> findAll();
    ReceiptItem findId(Long id);
    void save(ReceiptItem receiptItem);
    void delete(Long id);
}
