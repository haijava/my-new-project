package hai.exam1.controller;

import hai.exam1.model.Stastical;
import hai.exam1.service.StasticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class StasticalController {
      @Autowired
    private StasticalService stasticalService;
    @GetMapping("/stastical")
    public ModelAndView modelAndView(){
        ModelAndView modelAndView = new ModelAndView("/admin/stastical");
        return modelAndView;

    }
    @PostMapping("/stastical")
    public ModelAndView test(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime begin, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){
        Iterable<Stastical> stasticals = stasticalService.statiscal(begin, end);
        ModelAndView modelAndView = new ModelAndView("/admin/stastical");
        modelAndView.addObject("stastical",stasticals);
        modelAndView.addObject("begin",begin);
        modelAndView.addObject("end",end);
        return modelAndView;

    }
}
