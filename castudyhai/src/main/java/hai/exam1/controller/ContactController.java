package hai.exam1.controller;

import hai.exam1.model.Contact;
import hai.exam1.model.ProductUpload;
import hai.exam1.model.Staff;
import hai.exam1.service.CategoryService;
import hai.exam1.service.ContactService;
import hai.exam1.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StaffService staffService;

    @GetMapping("/create-contact")
    public ModelAndView showFormContact(@ModelAttribute("contact")Contact contact) {
        ModelAndView modelAndView = new ModelAndView("/product/contact");
        modelAndView.addObject("contact", new Contact());
        modelAndView.addObject("categories",categoryService.findAll());
        Iterable<Staff> staffs = staffService.findAll();
        modelAndView.addObject("staffs",staffs);
        return modelAndView;
    }
    @PostMapping("/create-contact")
    public  ModelAndView CreateContact(@ModelAttribute("contact") Contact contact ){
        contactService.save(contact);
        ModelAndView modelAndView = new ModelAndView("/product/contact");
        modelAndView.addObject("contact",new Contact());
        return modelAndView;
    }
}
