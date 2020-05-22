package hai.exam1.repository;

import hai.exam1.model.Login;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LoginRepository extends PagingAndSortingRepository<Login,Long> {
 Login findAllByEmailAndPassword(String email, String password);
}
