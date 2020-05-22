package hai.exam1.service;

import hai.exam1.model.Role;

public interface RoleService {
    Iterable<Role> findAll();
    Role findId(Long id);
    void save(Role role);
    void delete(Long id);
}
