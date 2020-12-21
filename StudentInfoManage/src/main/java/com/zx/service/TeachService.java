package com.zx.service;

import com.zx.bean.TeachBeanKey;
import com.zx.bean.TeacherInfoBean;

import java.util.List;

public interface TeachService {
    int deleteByPrimaryKey(String tid);
    int insert(TeacherInfoBean record);
    List<TeachBeanKey> selectByExample(TeachBeanKey teachBeanKey);
    TeachBeanKey selectByPrimaryKey(String tid);
    int update(TeachBeanKey record);

}
