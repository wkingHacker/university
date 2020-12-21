package com.zx.bean;

public class ChooseBeanKey {
    private String sid;

    private String cid;

    public ChooseBeanKey(String sid, String cid) {
        this.sid = sid;
        this.cid = cid;
    }

    public ChooseBeanKey() {
        super();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}