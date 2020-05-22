package hai.exam1.service.impl;

import hai.exam1.model.Role;
import hai.exam1.repository.RoleRepository;
import hai.exam1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findId(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);

    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
