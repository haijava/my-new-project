
package hai.exam1.service.impl;

import hai.exam1.model.Stastical;
import hai.exam1.repository.ReceiptRepository;
import hai.exam1.service.StasticalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class StasticalServiceImpl implements StasticalService {
    @Autowired
    private StasticalService stasticalService;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Override
    public Iterable<Stastical> statiscal(LocalDateTime begin, LocalDateTime end) {
        return receiptRepository.statiscal(begin, end);
    }
}

