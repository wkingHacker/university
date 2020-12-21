package com.zx.bean;

public class TeachBeanKey {
    private String tid;

    private String cid;




    private ClassInfoBean classInfoBean;//表示课程编号隶属于某个课程


    public ClassInfoBean getClassInfoBean() {
        return classInfoBean;
    }

    public void setClassInfoBean(ClassInfoBean classInfoBean) {
        this.classInfoBean = classInfoBean;
    }


    public TeachBeanKey(String tid, String cid) {
        this.tid = tid;
        this.cid = cid;

    }

    public TeachBeanKey(String tid, String cid,ClassInfoBean classInfoBean) {
        this.tid = tid;
        this.cid = cid;
        this.classInfoBean=classInfoBean;
    }

    public TeachBeanKey() {
        super();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }
}