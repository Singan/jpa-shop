package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class HomeController {

    @GetMapping
    public String home(ModelAndView modelAndView) {
        log.info("modelAndView.getViewName()" );
        modelAndView.setViewName("home");
        return "/home";
    }
}
