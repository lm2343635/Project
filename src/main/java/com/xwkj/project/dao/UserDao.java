package com.xwkj.project.dao;

import com.xwkj.common.hibernate.BaseDao;
import com.xwkj.project.domain.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    /**
     * 根据用户名查找用户
     *
     * @param uname
     * @return
     */
    User findUserByName(String uname);

}
