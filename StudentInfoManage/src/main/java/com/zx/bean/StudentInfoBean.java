package com.zx.bean;

public class StudentInfoBean {
    private String sid;

    private String sname;

    private String sex;

    private String loginid;

    
    public StudentInfoBean(String sid, String sname, String sex, String loginid) {
        this.sid = sid;
        this.sname = sname;
        this.sex = sex;
        this.loginid = loginid;
    }

    public StudentInfoBean() {
        super();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid == null ? null : loginid.trim();
    }
}