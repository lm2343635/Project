package com.xwkj.project.bean;

import com.xwkj.project.domain.User;
import org.directwebremoting.annotations.DataTransferObject;

import java.util.Date;

@DataTransferObject
public class UserBean {

    private String uid;
    private String uname;
    private String password;
    private Date createAt;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public UserBean(User user) {
        super();
        this.uid = user.getUid();
        this.uname = user.getUname();
        this.password = user.getPassword();
        this.createAt = new Date(user.getCreateAt());
    }

}
