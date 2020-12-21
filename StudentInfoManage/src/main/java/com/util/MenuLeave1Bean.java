package com.util;

import java.util.ArrayList;
import java.util.List;

public class MenuLeave1Bean {

    String title;
    String icon;
    List<MenuLeave2Bean> list = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuLeave2Bean> getList() {
        return list;
    }

    public void setList(List<MenuLeave2Bean> list) {
        this.list = list;
    }
}
