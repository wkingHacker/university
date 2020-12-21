package com.zx.bean;

public class TeacherInfoBean {
    private String tid;

    private String tname;

    private String sex;

    private String loginid;

    public TeacherInfoBean(String tid, String tname, String sex, String loginid) {
        this.tid = tid;
        this.tname = tname;
        this.sex = sex;
        this.loginid = loginid;
    }

    public TeacherInfoBean() {
        super();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
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