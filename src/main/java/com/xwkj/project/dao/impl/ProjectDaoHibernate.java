package com.xwkj.project.dao.impl;

import com.xwkj.common.hibernate.BaseHibernateDaoSupport;
import com.xwkj.common.util.DateTool;
import com.xwkj.project.dao.ProjectDao;
import com.xwkj.project.domain.Project;
import com.xwkj.project.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProjectDaoHibernate extends BaseHibernateDaoSupport<Project> implements ProjectDao {

    public ProjectDaoHibernate() {
        super();
        setClass(Project.class);
    }

    public List<Project> findByName(String name) {
        String hql = "from Project where name like %" + name + "%";
        return (List<Project>) getHibernateTemplate().find(hql, name);
    }

    public List<Project> findByUser(User user, boolean expire) {
        if (expire) {
            String hql = "from Project where user = ? and expireAt >= ? order by updateAt desc";
            return (List<Project>) getHibernateTemplate().find(hql, user, DateTool.previousDay(new Date()).getTime());
        } else {
            String hql = "from Project where user = ? order by updateAt desc";
            return (List<Project>) getHibernateTemplate().find(hql, user);
        }
    }

    public List<Project> findUnexpired() {
        String hql = "from Project where expireAt >= ? order by expireAt";
        return (List<Project>) getHibernateTemplate().find(hql, DateTool.previousDay(new Date()).getTime());
    }

}
