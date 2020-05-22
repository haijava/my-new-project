package hai.exam1.service;

import hai.exam1.model.Contact;
import hai.exam1.model.Staff;

public interface StaffService {
    Iterable<Staff> findAll();
    Staff findId(Long id);
    void save(Staff staff);
    void delete(Long id);
}
