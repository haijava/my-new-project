package hai.exam1.controller;

import hai.exam1.model.Contact;
import hai.exam1.model.Staff;
import hai.exam1.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;
    @GetMapping("/create-staff")
    public ModelAndView showFormContact(@ModelAttribute("staff") Staff staff) {
        ModelAndView modelAndView = new ModelAndView("/admin/staff");
        modelAndView.addObject("staff", new Staff());
        return modelAndView;
    }
    @PostMapping("/create-staff")
    public  ModelAndView CreateContact(@ModelAttribute("contact")Staff staff){
        staffService.save(staff);
        ModelAndView modelAndView = new ModelAndView("/admin/staff");
        modelAndView.addObject("staff",new Contact());
        return modelAndView;
    }
    @GetMapping("/viewS")
    public ModelAndView show(){
        Iterable<Staff> staffs = staffService.findAll();
        ModelAndView modelAndView = new ModelAndView("/product/contact");
        modelAndView.addObject("staffs",staffs);
        return modelAndView;

    }
}
