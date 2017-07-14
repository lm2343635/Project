package com.xwkj.project.bean;

import com.xwkj.project.domain.Project;

import java.util.Date;
import java.util.Map;

public class ProjectBean {

    private String pid;
    private String name;
    private Date createAt;
    private Date updateAt;
    private String attributes;
    private String content;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ProjectBean(Project project, boolean simple) {
        this.pid = project.getPid();
        this.name = project.getName();
        this.createAt = new Date(project.getCreateAt());
        this.updateAt = new Date(project.getUpdateAt());
        if (!simple) {
            this.attributes = project.getAttributes();
            this.content = project.getContent();
        }
    }

}
