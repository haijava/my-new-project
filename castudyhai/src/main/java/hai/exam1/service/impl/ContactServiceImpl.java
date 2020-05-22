package hai.exam1.service.impl;

import hai.exam1.model.Contact;
import hai.exam1.repository.ContactRepository;
import hai.exam1.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findId(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Contact contact) {
contactRepository.save(contact);
    }

    @Override
    public void delete(Long id) {
contactRepository.deleteById(id);
    }
}
