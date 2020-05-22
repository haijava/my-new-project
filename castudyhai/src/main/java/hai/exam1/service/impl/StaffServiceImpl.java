package hai.exam1.service.impl;

import hai.exam1.model.Staff;
import hai.exam1.repository.StaffRepository;
import hai.exam1.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;

public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Override
    public Iterable<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findId(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Staff staff) {
staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
staffRepository.deleteById(id);
    }
}
