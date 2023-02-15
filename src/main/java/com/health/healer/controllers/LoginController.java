package com.health.healer.controllers;/*Hi Nick*/
import com.health.healer.connections.HttpSessionBean;
import com.health.healer.service.LoginService;
import com.health.healer.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.SQLException;

@Controller

public class LoginController {
    @Autowired
    private HttpSessionBean httpSessionBean;
    @Autowired
    private LoginService loginService;

    @Autowired
    private WorkerService workerService;

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

        httpSessionBean.setId(workerService.findIdByLogin(httpSessionBean.getConnection(), name));

        String role = loginService.getRole(name, httpSessionBean.getConnection());

        System.out.print(role);

        if (role.equals("doctor"))
        {
            return "redirect:/doctorMain";
        }
        else if (role.equals("user"))
        {
            return "redirect:/userMain";
        }
        else if (role.equals("lab"))
        {
            return "redirect:/labMain";
        }
        else if (role.equals("physio"))
        {
            return "redirect:/physioMain";
        }
        else if (role.equals("admin"))
        {
            return "redirect:/adminMain";
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
