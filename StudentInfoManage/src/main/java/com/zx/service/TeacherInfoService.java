package com.zx.service;


import com.zx.bean.TeacherInfoBean;

import java.util.List;

public interface TeacherInfoService {
    int deleteByPrimaryKey(String tid);
    int insert(TeacherInfoBean record);
    List<TeacherInfoBean> selectByExample(TeacherInfoBean teacherInfoBean);
    TeacherInfoBean selectByPrimaryKey(String tid);
    int update(TeacherInfoBean record);

    /**
     * 根据id实现批量删除
     * @param tid
     * @return
     */
    int deleteUserAllById(List<String> tid);
}
