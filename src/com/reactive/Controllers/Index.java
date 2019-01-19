package com.reactive.Controllers;
import com.pojo.TSysRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Controller
public class Index {
    @RequestMapping("/test")
   /* @ResponseBody*/
    public String test() {
        return "test";
    }

    @RequestMapping("/toindex")
    public String index() {
 /* TSysRole tSysRole = new TSysRole();
        tSysRole.setId(10);
        tSysRole.setRoleName("admin");
        model.addAttribute("role", TSysRole.class);*/
        return "index";
    }

    @ModelAttribute("/role")
    public TSysRole role() {
        return new TSysRole(1, "User");
    }
        @ModelAttribute("/roles")
        public List<TSysRole>roles(){
            return Arrays.asList(new TSysRole(1, "ADMIN"), new TSysRole(2, "Tomcat"));
        }

    }
