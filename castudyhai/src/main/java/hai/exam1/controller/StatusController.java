package hai.exam1.controller;

import hai.exam1.model.Category;
import hai.exam1.model.Product;
import hai.exam1.model.Status;
import hai.exam1.service.ProductService;
import hai.exam1.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatusController {
    @Autowired
    private StatusService statusService;
    @GetMapping("/create-status")
    public ModelAndView showFormStatus() {
        ModelAndView modelAndView = new ModelAndView("/status/status");
        modelAndView.addObject("status", new Status());
        return modelAndView;
    }

    @PostMapping("/create-status")
    public ModelAndView create(@ModelAttribute("status") Status status) {
        ModelAndView modelAndView = new ModelAndView("/status/status");
        statusService.save(status);
        modelAndView.addObject("status", new Status());
        modelAndView.addObject("messeage", "a New Status was create !!!");
        return modelAndView;
    }
}
