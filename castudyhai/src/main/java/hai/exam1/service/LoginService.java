package hai.exam1.service;
import hai.exam1.model.Login;

public interface LoginService {
    Iterable<Login>findAll();
    Login findById(Long id);
    void  save(Login login);
    void delete(Long id);
  Login findByEmailPass(String email, String password);
}
