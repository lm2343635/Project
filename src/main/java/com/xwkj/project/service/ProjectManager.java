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
    String addProject(String name, String attributes, String content, long expireAt, String uid, HttpSession session);

    /**
     * Admin modify a project.
     *
     * @param pid
     * @param name
     * @param attributes
     * @param content
     * @return
     */
    boolean modifyProject(String pid, String name, String attributes, String content, long expireAt, HttpSession session);

    /**
     * Admin remove a project.
     *
     * @param pid
     * @return
     */
    boolean removeProject(String pid, HttpSession session);

    /**
     * Search project.
     *
     * @param name
     * @return
     */
    List<ProjectBean> search(String name, HttpSession session);

    /**
     * Admin get projects by uid.
     *
     * @param uid
     * @param session
     * @return
     */
    List<ProjectBean> getProjectsByUid(String uid, HttpSession session);

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
     * @return
     */
    ProjectBean getProject(String pid);

}
