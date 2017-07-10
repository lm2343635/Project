package com.xwkj.project.service.common;

import com.xwkj.project.service.AdminManager;

import javax.servlet.http.HttpSession;

public class ManagerTemplate {


    public boolean checkAdminSession(HttpSession session) {
        return session.getAttribute(AdminManager.ADMIN_FLAG) != null;
    }

}
