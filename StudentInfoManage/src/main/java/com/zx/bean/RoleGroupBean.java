package com.zx.bean;

public class RoleGroupBean {
    private String groupid;

    private String groupname;

    public RoleGroupBean(String groupid, String groupname) {
        this.groupid = groupid;
        this.groupname = groupname;
    }

    public RoleGroupBean() {
        super();
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }
}