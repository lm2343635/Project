package com.xwkj.project.controller;

import com.xwkj.project.service.AdminManager;
import com.xwkj.project.service.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(AdminManager.ADMIN_FLAG);
        response.sendRedirect("/admin");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void userLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(UserManager.USER_FLAG);
        response.sendRedirect("/user");
    }

}
