package hai.exam1.service;

import hai.exam1.model.Receipt;
import hai.exam1.model.Status;

public interface StatusService {
    Iterable<Status> findAll();
    Status findId(Long id);
    void save(Status status);
    void delete(Long id);
}
