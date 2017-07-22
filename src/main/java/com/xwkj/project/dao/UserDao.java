package com.xwkj.project.dao;

import com.xwkj.common.hibernate.BaseDao;
import com.xwkj.project.domain.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    /**
     * Find user by name
     *
     * @param uname
     * @return
     */
    User getByName(String uname);

    /**
     * Search user by keyword of name
     *
     * @param keyword
     * @return
     */
    List<User> searchByName(String keyword);

}
