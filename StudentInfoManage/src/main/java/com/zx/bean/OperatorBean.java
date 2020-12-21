package com.zx.bean;
import java.util.Date;


public class OperatorBean {
    private String loginid;

    private String groupid;

    private String loginname;

    private String loginpwd;

    private String LastLoginIp;
    private Date LastLoginTime;
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public OperatorBean(String loginid, String groupid, String loginname, String loginpwd, String lastLoginIp, Date lastLoginTime, String src, RoleGroupBean roleGroupBeans) {
        this.loginid = loginid;
        this.groupid = groupid;
        this.loginname = loginname;
        this.loginpwd = loginpwd;
        LastLoginIp = lastLoginIp;
        LastLoginTime = lastLoginTime;
        this.src = src;
        this.roleGroupBeans = roleGroupBeans;
    }

    public OperatorBean(String loginid, String groupid, String loginname, String loginpwd, String lastLoginIp, Date lastLoginTime, RoleGroupBean roleGroupBeans) {
        this.loginid = loginid;
        this.groupid = groupid;
        this.loginname = loginname;
        this.loginpwd = loginpwd;
        LastLoginIp = lastLoginIp;
        LastLoginTime = lastLoginTime;
        this.roleGroupBeans = roleGroupBeans;
    }

    public String getLastLoginIp() {
        return LastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        LastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    RoleGroupBean roleGroupBeans;


    public RoleGroupBean getRoleGroupBeans() {
        return roleGroupBeans;
    }

    public OperatorBean(String loginid, String groupid, String loginname, String loginpwd, RoleGroupBean roleGroupBeans) {
        this.loginid = loginid;
        this.groupid = groupid;
        this.loginname = loginname;
        this.loginpwd = loginpwd;
        this.roleGroupBeans = roleGroupBeans;
    }

    public void setRoleGroupBeans(RoleGroupBean roleGroupBeans) {
        this.roleGroupBeans = roleGroupBeans;
    }

    public OperatorBean(String loginid, String groupid, String loginname, String loginpwd) {
        this.loginid = loginid;
        this.groupid = groupid;
        this.loginname = loginname;
        this.loginpwd = loginpwd;
    }

    public OperatorBean() {
        super();
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid == null ? null : loginid.trim();
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd == null ? null : loginpwd.trim();
    }
}