package hai.exam1.service.impl;

import hai.exam1.model.Customer;
import hai.exam1.repository.CustomerRepository;
import hai.exam1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findId(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
customerRepository.deleteById(id);
    }
}
