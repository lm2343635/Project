package com.xwkj.project.service;


import com.xwkj.project.bean.UserBean;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserManager {

    public static final String USER_FLAG = "8aa501824fb5fb3c014fb607fe100000";

    /**
     * 新增用户
     *
     * @param uname
     * @param password
     * @return
     */
    String addUser(String uname, String password);

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    boolean deleteUser(String uid);

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
     * @param session
     * @return
     */
    List<UserBean> getAll(HttpSession session);

    /**
     * 修改密码
     *
     * @param uid
     * @param old
     * @param password
     */
    boolean modifyPassword(String uid, String old, String password);

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

    /**
     * 管理员修改用户信息
     *
     * @param uid
     * @param uname
     * @param password
     * @param tid
     * @return
     */
    void modifyUserInfo(String uid, String uname, String password, String tid);

}
