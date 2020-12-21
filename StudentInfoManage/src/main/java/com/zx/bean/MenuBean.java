package com.zx.bean;

public class MenuBean {
    private String menuid;

    private String parentid;

    private String menuname;

    private String menuurl;

    private String title;

    public MenuBean(String menuid, String parentid, String menuname, String menuurl, String title) {
        this.menuid = menuid;
        this.parentid = parentid;
        this.menuname = menuname;
        this.menuurl = menuurl;
        this.title = title;
    }

    public MenuBean() {
        super();
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getParentmenuid() {
        return null;
    }
}