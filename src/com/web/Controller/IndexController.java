package com.web.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/usr/index")
    public String index(){
        return "Index";
    }
    @RequestMapping("/toindex")
    public String toindex(){
        return "Index";
    }
@RequestMapping("/admin/toAdminIndex")
public  String toAdminIndex(Model model){
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("user",authentication.getPrincipal());
        return "admin";
}
@GetMapping("/login")
public String tologin(){
        return "login";
}
    @PostMapping("/errorlogin")
    public String toLoginErrorPage() {
        return "login";
    }
   @RequestMapping("/errorlogin")
    public String tologins(){
        return "login";
    }
}
