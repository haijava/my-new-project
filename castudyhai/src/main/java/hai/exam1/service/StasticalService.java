
package hai.exam1.service;

import hai.exam1.model.Stastical;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface StasticalService {
    Iterable<Stastical> statiscal(LocalDateTime begin, LocalDateTime end);
}

