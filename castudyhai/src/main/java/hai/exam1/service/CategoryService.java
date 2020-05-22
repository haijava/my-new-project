package hai.exam1.service;

import hai.exam1.model.Category;

import java.util.Iterator;

public interface CategoryService {
    Iterable<Category>findAll();
    Category findId(Long id);
    void save(Category category);
    void delete(Long id);
}
