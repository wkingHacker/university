package com.zx.bean;

import java.util.Date;

public class ManagerBean {
    private String managerid;

    private String loginid;

    private String username;

   private Date LastLoginTime;
   private String LastLoginIp;

    public ManagerBean() {
    }

    public ManagerBean(String managerid, String loginid, String username, Date lastLoginTime, String lastLoginIp) {
        this.managerid = managerid;
        this.loginid = loginid;
        this.username = username;
        LastLoginTime = lastLoginTime;
        LastLoginIp = lastLoginIp;
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return LastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        LastLoginIp = lastLoginIp;
    }

    @Override
    public String toString() {
        return "ManagerBean{" +
                "managerid='" + managerid + '\'' +
                ", loginid='" + loginid + '\'' +
                ", username='" + username + '\'' +
                ", LastLoginTime='" + LastLoginTime + '\'' +
                ", LastLoginIp='" + LastLoginIp + '\'' +
                '}';
    }
}