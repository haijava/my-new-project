package hai.exam1.controller;

import hai.exam1.model.Category;
import hai.exam1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/lica")
    public ModelAndView list(){
        Iterable<Category>categories =categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("category",categories);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("category") Category category){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        categoryService.save(category);
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("message","a new Category was create");
        return modelAndView;
    }

}
