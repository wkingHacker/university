package com.zx.service;

import com.zx.bean.StudentInfoBean;
import com.zx.bean.StudentInfoBeanExample;

import java.util.List;

/**
 * @author WCY
 * @create 2020-08-18 17:05
 */
public interface StudentInfoService {
    int deleteByPrimaryKey(String operatorid);
    int insert(StudentInfoBean record);
    int update(StudentInfoBean record);
    StudentInfoBean selectByPrimaryKey(String operatorid);
    List<StudentInfoBean> selectByExample(StudentInfoBean studentInfoBean);
    int countByExample(StudentInfoBeanExample example);
}
