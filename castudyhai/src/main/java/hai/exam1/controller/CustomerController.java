package hai.exam1.controller;

import hai.exam1.model.Category;
import hai.exam1.model.Customer;
import hai.exam1.service.CategoryService;
import hai.exam1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/customer")
    public ModelAndView showCustomer() {
        ModelAndView modelAndView = new ModelAndView("/product/order-info");
        modelAndView.addObject("customer", new Customer());
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories",categories);

        return modelAndView;
    }
    @PostMapping("/customer")
    public ModelAndView uploadCustomer(@ModelAttribute("customer") Customer customer) {
         customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/product/order-info");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

}
