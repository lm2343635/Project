package com.xwkj.project.dao.impl;

import com.xwkj.common.hibernate.BaseHibernateDaoSupport;
import com.xwkj.project.dao.UserDao;
import com.xwkj.project.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoHibernate extends BaseHibernateDaoSupport<User> implements UserDao {

    public UserDaoHibernate() {
        super();
        setClass(User.class);
    }

    public User findUserByName(String uname) {
        String hql = "from User where uname = ?";
        List<User> users = (List<User>) getHibernateTemplate().find(hql, uname);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

}
