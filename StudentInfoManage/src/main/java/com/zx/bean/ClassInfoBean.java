package com.zx.bean;

public class ClassInfoBean {
    private String cid;

    private String cname;

    public ClassInfoBean(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public ClassInfoBean() {
        super();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}