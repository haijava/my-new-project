package hai.exam1.controller;

import hai.exam1.model.Login;
import hai.exam1.model.Product;
import hai.exam1.service.CategoryService;
import hai.exam1.service.LoginService;
import hai.exam1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/log")
    public ModelAndView showLogin() {
        ModelAndView modelAndView = new ModelAndView("/login/login");
        modelAndView.addObject("logins", new Login());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/login/login");
        modelAndView.addObject("logins", new Login());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("logins") Login login) {
        Login login1 = loginService.findByEmailPass(login.getEmail(), login.getPassword());
        if (login1 != null) {
           Iterable<Product> products= productService.findAll();
            ModelAndView modelAndView = new ModelAndView("/admin/admin");
            modelAndView.addObject("products",products);
            modelAndView.addObject("categories",categoryService.findAll());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/login/login");
            modelAndView.addObject("logins", login);
            return modelAndView;

        }
    }

    @GetMapping("/create-login")
    public ModelAndView showFormLogin() {
        ModelAndView modelAndView = new ModelAndView("/login/create");
        modelAndView.addObject("login", new Login());
        return modelAndView;
    }

    @PostMapping("/create-login")
    public ModelAndView createUser(@Validated @ModelAttribute("login") Login login, BindingResult bindingResult) {
                if (bindingResult.hasErrors()) {
                    ModelAndView modelAndView = new ModelAndView("/login/create");
                    /*modelAndView.addObject("login",new Login());*/
                    return modelAndView;
                }
                Iterable<Login> logins = loginService.findAll();
                for (Login login1:logins){
                    if (login1.getEmail().equals(login.getEmail())){
                        ModelAndView modelAndView = new ModelAndView("/login/create");
                        modelAndView.addObject("messagemail","Email da ton tai");
                        return modelAndView;
                    }
                }
                loginService.save(login);
                ModelAndView modelAndView = new ModelAndView("/login/create");
                modelAndView.addObject("logins", new Login());
                modelAndView.addObject("message", "a new user was create");
                return modelAndView;
            }
        }




