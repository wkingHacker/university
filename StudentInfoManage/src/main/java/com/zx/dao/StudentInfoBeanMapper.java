package com.zx.dao;

import com.zx.bean.StudentInfoBean;
import com.zx.bean.StudentInfoBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoBeanMapper {
    int countByExample(StudentInfoBeanExample example);

    int deleteByExample(StudentInfoBeanExample example);

    int deleteByPrimaryKey(String sid);

    int insert(StudentInfoBean record);

    int insertSelective(StudentInfoBean record);

    List<StudentInfoBean> selectByExample(StudentInfoBean example);

    StudentInfoBean selectByPrimaryKey(String sid);

    int updateByExampleSelective(@Param("record") StudentInfoBean record, @Param("example") StudentInfoBeanExample example);

    int updateByExample(@Param("record") StudentInfoBean record, @Param("example") StudentInfoBeanExample example);

    int updateByPrimaryKeySelective(StudentInfoBean record);

    int updateByPrimaryKey(StudentInfoBean record);
}