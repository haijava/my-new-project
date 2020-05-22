package hai.exam1.service.impl;

import hai.exam1.model.Status;
import hai.exam1.repository.StatusRepository;
import hai.exam1.service.StasticalService;
import hai.exam1.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusServicempl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findId(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Status status) {
statusRepository.save(status);
    }

    @Override
    public void delete(Long id) {
statusRepository.deleteById(id);
    }
}
