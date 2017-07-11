package com.xwkj.project.service.impl;

import com.xwkj.project.bean.UserBean;
import com.xwkj.project.domain.User;
import com.xwkj.project.service.UserManager;
import com.xwkj.project.service.common.ManagerTemplate;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RemoteProxy(name = "UserManager")
public class UserManagerImpl extends ManagerTemplate implements UserManager {

    @RemoteMethod
    @Transactional
    public String addUser(String uname, String password) {
        User user = new User();
        user.setUname(uname);
        user.setPassword(password);
        return userDao.save(user);
    }

    @RemoteMethod
    @Transactional
    public boolean deleteUser(String uid) {
        User user = userDao.get(uid);
        userDao.delete(user);
        return true;
    }

    @RemoteMethod
    public UserBean getUser(String uid) {
        User user = userDao.get(uid);
        if (user == null) {
            return null;
        }
        return new UserBean(user);
    }

    public List<UserBean> getAll(HttpSession session) {
        if (!checkAdminSession(session)) {
            return null;
        }
        List<UserBean> userBeans = new ArrayList<UserBean>();
        for (User user : userDao.findAll()) {
            userBeans.add(new UserBean(user));
        }
        return userBeans;
    }

    @RemoteMethod
    @Transactional
    public boolean modifyPassword(String uid, String old, String password) {
        User user = userDao.get(uid);
        if (!user.getPassword().equals(old)) {
            return false;
        }
        user.setPassword(password);
        userDao.update(user);
        return true;
    }

    @RemoteMethod
    public boolean login(String uname, String password, HttpSession session) {
        User user = userDao.findUserByName(uname);
        if (user == null) {
            return false;
        }
        if (user.getPassword().equals(password)) {
            UserBean userBean = new UserBean(user);
            session.setAttribute(USER_FLAG, userBean);
            return true;
        }
        return false;
    }

    @RemoteMethod
    public UserBean checkSession(HttpSession session) {
        return getUserBeanFromSession(session);
    }

    @RemoteMethod
    @Transactional
    public void modifyUserInfo(String uid, String uname, String password, String tid) {
        User user = userDao.get(uid);
        user.setUname(uname);
        user.setPassword(password);
        userDao.update(user);
    }

}
