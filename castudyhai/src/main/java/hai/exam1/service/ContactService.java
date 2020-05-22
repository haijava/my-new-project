package hai.exam1.service;

import hai.exam1.model.Contact;
import hai.exam1.model.Product;

public interface ContactService {
    Iterable<Contact> findAll();
    Contact findId(Long id);
    void save(Contact contact);
    void delete(Long id);
}
