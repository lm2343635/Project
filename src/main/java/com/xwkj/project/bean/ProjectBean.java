package com.xwkj.project.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwkj.project.domain.Project;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProjectBean {

    private String pid;
    private String name;
    private Date createAt;
    private Date updateAt;
    private Map<String, String> attributes;
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
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
            try {
                this.attributes = new ObjectMapper().readValue(project.getAttributes(), HashMap.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.content = project.getContent();
        }
    }

}
