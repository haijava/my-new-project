package hai.exam1.service.impl;

import hai.exam1.model.Receipt;
import hai.exam1.model.ReceiptItem;
import hai.exam1.repository.ReceiptItemRepository;
import hai.exam1.service.ReceiptItemService;
import hai.exam1.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiptItemServiceImpl implements ReceiptItemService {

    @Autowired
    private ReceiptItemRepository receiptItemRepository;

    @Override
    public Iterable<ReceiptItem> findAll() {
        return receiptItemRepository.findAll();
    }

    @Override
    public ReceiptItem findId(Long id) {
        return receiptItemRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ReceiptItem receiptItem) {
receiptItemRepository.save(receiptItem);
    }

    @Override
    public void delete(Long id) {
receiptItemRepository.deleteById(id);
    }
}
