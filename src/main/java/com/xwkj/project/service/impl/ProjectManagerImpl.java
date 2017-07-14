package com.xwkj.project.service.impl;

import com.xwkj.project.bean.ProjectBean;
import com.xwkj.project.domain.Project;
import com.xwkj.project.domain.User;
import com.xwkj.project.service.ProjectManager;
import com.xwkj.project.service.common.ManagerTemplate;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RemoteProxy(name = "ProjectManager")
public class ProjectManagerImpl extends ManagerTemplate implements ProjectManager {

    @RemoteMethod
    @Transactional
    public String addProject(String name, String attributes, String content, String uid, HttpSession session) {
        if (!checkAdminSession(session)) {
            return null;
        }
        User user = userDao.get(uid);
        if (user == null) {
            return null;
        }
        Project project = new Project();
        project.setName(name);
        project.setAttributes(attributes);
        project.setContent(content);
        project.setCreateAt(System.currentTimeMillis());
        project.setUpdateAt(project.getCreateAt());
        project.setUser(user);
        return projectDao.save(project);
    }

    @RemoteMethod
    @Transactional
    public boolean modifyProject(String pid, String name, String attributes, String content, HttpSession session) {
        if (!checkAdminSession(session)) {
            return false;
        }
        Project project = projectDao.get(pid);
        if (project == null) {
            return false;
        }
        project.setName(name);
        project.setAttributes(attributes);
        project.setContent(content);
        project.setUpdateAt(System.currentTimeMillis());
        projectDao.update(project);
        return true;
    }

    @RemoteMethod
    @Transactional
    public boolean removeProject(String pid, HttpSession session) {
        if (!checkAdminSession(session)) {
            return false;
        }
        Project project = projectDao.get(pid);
        if (project == null) {
            return false;
        }
        projectDao.delete(project);
        return true;
    }

    @RemoteMethod
    public List<ProjectBean> search(String name, HttpSession session) {
        List<ProjectBean> projectBeans = new ArrayList<ProjectBean>();
        for (Project project : projectDao.findByName(name)) {
            projectBeans.add(new ProjectBean(project, true));
        }
        return projectBeans;
    }

    @RemoteMethod
    public List<ProjectBean> getProjectsByUid(String uid, HttpSession session) {
        if (!checkAdminSession(session)) {
            return null;
        }
        User user = userDao.get(uid);
        if (user == null) {
            return null;
        }
        List<ProjectBean> projectBeans = new ArrayList<ProjectBean>();
        for (Project project : projectDao.findByUser(user)) {
            projectBeans.add(new ProjectBean(project, true));
        }
        return projectBeans;
    }

    @RemoteMethod
    public List<ProjectBean> getProjectsForUser(HttpSession session) {
        User user = getUserFromSession(session);
        if (user == null) {
            return null;
        }
        List<ProjectBean> projectBeans = new ArrayList<ProjectBean>();
        for (Project project : projectDao.findByUser(user)) {
            projectBeans.add(new ProjectBean(project, true));
        }
        return projectBeans;
    }

    @RemoteMethod
    public ProjectBean getProject(String pid) {
        Project project = projectDao.get(pid);
        if (project == null) {
            return null;
        }
        return new ProjectBean(project, false);
    }

}
