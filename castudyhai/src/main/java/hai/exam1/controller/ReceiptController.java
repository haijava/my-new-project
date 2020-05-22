package hai.exam1.controller;

import hai.exam1.model.Product;
import hai.exam1.model.Receipt;
import hai.exam1.model.Stastical;
import hai.exam1.service.ReceiptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReceiptController {
   /* @Autowired
    private ReceiptService receiptService;
    @GetMapping("/stastical")
    public ModelAndView modelAndView(){
        Iterable<Stastical> stasticals = receiptService.statiscal();
        ModelAndView modelAndView = new ModelAndView("/admin/stastical");
        modelAndView.addObject("stastical",stasticals);
        return modelAndView;

    }*/
}
