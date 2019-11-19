package com.yao.web.admin;

import com.yao.po.User;
import com.yao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public  String tologin(){
        return "admin/login";
    }
    @PostMapping("/login")
    public  String login(@RequestParam String username, @RequestParam String password,
                         HttpSession session, RedirectAttributes attributes){
        User user=userService.userCheck(username,password);
        if (user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";

        }else {
            attributes.addFlashAttribute("message","账号和密码错误");
            return "redirect:/admin";

        }
    }
}
