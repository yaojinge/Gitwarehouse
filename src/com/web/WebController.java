package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/index")
    public String index(){
        return "Index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
