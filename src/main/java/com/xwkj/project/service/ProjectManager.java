package com.xwkj.project.service;

import com.xwkj.project.bean.ProjectBean;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ProjectManager {

    /**
     * Admin create a project for a user.
     *
     * @param name
     * @param attributes
     * @param content
     * @param uid
     * @return
     */
    String addProject(String name, String attributes, String content, String uid, HttpSession session);

    /**
     * Admin modify a project.
     *
     * @param did
     * @param name
     * @param attributes
     * @param content
     * @return
     */
    boolean modifyProject(String did, String name, String attributes, String content, HttpSession session);

    /**
     * Admin remove a project.
     *
     * @param did
     * @return
     */
    boolean removeProject(String did, HttpSession session);

    /**
     * Search project.
     *
     * @param name
     * @return
     */
    List<ProjectBean> search(String name, HttpSession session);

    /**
     * Get projects list for a user.
     *
     * @param session
     * @return
     */
    List<ProjectBean> getProjectsForUser(HttpSession session);

    /**
     * Get project detail info.
     *
     * @param pid
     * @param session
     * @return
     */
    ProjectBean getProject(String pid, HttpSession session);

}
