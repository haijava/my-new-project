package hai.exam1.service.impl;

import hai.exam1.model.Category;
import hai.exam1.repository.CategoryRepositotry;
import hai.exam1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepositotry categoryRepositotry;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepositotry.findAll();
    }

    @Override
    public Category findId(Long id) {
        return categoryRepositotry.findById(id).orElse(null);
    }

    @Override
    public void save(Category category) {
        categoryRepositotry.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepositotry.deleteById(id);
    }
}
