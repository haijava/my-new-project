package hai.exam1.service.impl;

import hai.exam1.model.Login;
import hai.exam1.repository.LoginRepository;
import hai.exam1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceimpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Iterable<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Login findById(Long id) {
        return loginRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Login login) {
        loginRepository.save(login);

    }

    @Override
    public void delete(Long id) {
        loginRepository.deleteById(id);
    }

    @Override
    public Login findByEmailPass(String email, String password) {
        return loginRepository.findAllByEmailAndPassword(email, password);
    }

}
