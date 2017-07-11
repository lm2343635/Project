package com.xwkj.project.service.common;

import com.xwkj.project.bean.UserBean;
import com.xwkj.project.component.ConfigComponent;
import com.xwkj.project.dao.ProjectDao;
import com.xwkj.project.dao.UserDao;
import com.xwkj.project.domain.User;
import com.xwkj.project.service.AdminManager;
import com.xwkj.project.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class ManagerTemplate {

    @Autowired
    protected ConfigComponent configComponent;

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected ProjectDao projectDao;

    public ConfigComponent getConfigComponent() {
        return configComponent;
    }

    public void setConfigComponent(ConfigComponent configComponent) {
        this.configComponent = configComponent;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public boolean checkAdminSession(HttpSession session) {
        return session.getAttribute(AdminManager.ADMIN_FLAG) != null;
    }

    public UserBean getUserBeanFromSession(HttpSession session) {
        if (session.getAttribute(UserManager.USER_FLAG) == null) {
            return null;
        }
        return (UserBean) session.getAttribute(UserManager.USER_FLAG);
    }

    public User getUserFromSession(HttpSession session) {
        UserBean userBean = getUserBeanFromSession(session);
        User user = userDao.get(userBean.getUid());
        return user;
    }

}
