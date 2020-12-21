package com.zx.bean;

public class RoleMenuBeanKey {
    private String menuid;

    private String groupid;

    public RoleMenuBeanKey(String menuid, String groupid) {
        this.menuid = menuid;
        this.groupid = groupid;
    }

    public RoleMenuBeanKey() {
        super();
    }

    public String getMenuid(String menuid) {
        return this.menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
    }
}