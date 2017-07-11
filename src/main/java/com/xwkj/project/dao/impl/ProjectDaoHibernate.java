package com.xwkj.project.dao.impl;

import com.xwkj.common.hibernate.BaseHibernateDaoSupport;
import com.xwkj.project.dao.ProjectDao;
import com.xwkj.project.domain.Project;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoHibernate extends BaseHibernateDaoSupport<Project> implements ProjectDao {

    public ProjectDaoHibernate() {
        super();
        setClass(Project.class);
    }

}
