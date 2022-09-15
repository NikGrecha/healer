package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.SQLException;

@Controller
@RequiredArgsConstructor

public class LoginController {
    @Autowired
    private HttpSessionBean httpSessionBean;
    private final LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login/authorization")
    public String loginAuth(@RequestParam (name = "name") String name,
                            @RequestParam (name = "password") String password) throws SQLException {
        if (httpSessionBean.getConnection()!=null)
        {
            httpSessionBean.getConnection().close();
        }
        httpSessionBean.setConnection(loginService.getConnection(name, password));

        String role = loginService.getRole(name, httpSessionBean.getConnection());

        if (role.equals("doctor"))
        {
            return "redirect:/doctorMain";
        }
        else{
            return "redirect:/guestMain";
        }
    }

    @GetMapping("/logout")
    public String logout() throws SQLException {
        httpSessionBean.getConnection().close();
        httpSessionBean.setConnection(null);
        return "redirect:/login";
    }
}
