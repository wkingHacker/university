package com.zx.bean;

/**
 * @author Wking
 * @create 2020-08-20 11:54
 */
public class Photo {
    String loginname;
    String src;

    public Photo() {
    }

    public Photo(String LOGINNAME, String src) {
        this.loginname = LOGINNAME;
        this.src = src;
    }

    public String getLOGINNAME() {
        return loginname;
    }

    public void setLOGINNAME(String LOGINNAME) {
        this.loginname = LOGINNAME;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "LOGINNAME='" + loginname + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
