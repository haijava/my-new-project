package hai.exam1.service;

import hai.exam1.model.Contact;
import hai.exam1.model.Customer;

public interface CustomerService {
    Iterable<Customer> findAll();
    Customer findId(Long id);
    void save(Customer customer);
    void delete(Long id);
}
