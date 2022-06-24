package com.slvr.one.idslvrone.controllers.web;

import com.slvr.one.idslvrone.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController extends BasePageController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        User crtUser = this.getUser();
        if(crtUser!=null) {
            modelAndView.addObject("user", crtUser.getUsername());
        }
        return modelAndView;
    }

    @PostMapping
    public String indexPost() {
        return  "index";
    }
}
