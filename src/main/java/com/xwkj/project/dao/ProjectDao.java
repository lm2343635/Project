package com.xwkj.project.dao;

import com.xwkj.common.hibernate.BaseDao;
import com.xwkj.project.domain.Project;
import com.xwkj.project.domain.User;

import java.util.List;

public interface ProjectDao extends BaseDao<Project> {

    /**
     * Search projects by name.
     *
     * @param name
     * @return
     */
    List<Project> findByName(String name);

    /**
     * Find projects by user.
     *
     * @param user
     * @param expire
     * @return
     */
    List<Project> findByUser(User user, boolean expire);

    /**
     * Find all unexpired
     * @return
     */
    List<Project> findUnexpired();

}
