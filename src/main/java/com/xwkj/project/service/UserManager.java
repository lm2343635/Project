package com.xwkj.project.service;


import com.xwkj.project.bean.UserBean;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserManager {

    public static final String USER_FLAG = "8aa501824fb5fb3c014fb607fe100000";

    /**
     * Is user name existing in user list.
     *
     * @param name
     * @return
     */
    boolean isUserExist(String name);

    /**
     * Admin add new user.
     *
     * @param uname
     * @param password
     * @return
     */
    String addUser(String uname, String password, HttpSession session);

    /**
     * Admin remove a user
     *
     * @param uid
     * @return
     */
    boolean removeUser(String uid, HttpSession session);

    /**
     * 通过uid获得用户
     *
     * @param uid
     * @return
     */
    UserBean getUser(String uid);

    /**
     * Get all user list for admin.
     *
     * @param keyword
     * @param session
     * @return
     */
    List<UserBean> search(String keyword, HttpSession session);

    /**
     * User modify password
     *
     * @param old
     * @param password
     * @param session
     */
    boolean modifyPassword(String old, String password, HttpSession session);

    /**
     * 处理用户登录
     *
     * @param uname
     * @param password
     * @param session
     * @return
     */
    boolean login(String uname, String password, HttpSession session);

    /**
     * 检查用户session
     *
     * @param session
     * @return
     */
    UserBean checkSession(HttpSession session);

}
